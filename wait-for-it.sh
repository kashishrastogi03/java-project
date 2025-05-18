#!/usr/bin/env bash

# wait-for-it.sh
# This script waits for a TCP host:port to become available.
# You can optionally run a command after the check is successful.

CMD_NAME=${0##*/}

# Default values
QUIET=0
STRICT=0
CHILD=0
TIMEOUT=60
IS_BUSYBOX=0
BUSY_TIMEOUT_FLAG=""
CLI_CMD=()

echoerr() {
  [[ $QUIET -ne 1 ]] && echo "$@" 1>&2
}

usage() {
  cat <<EOF >&2
Usage:
  $CMD_NAME host:port [-s] [-t timeout] [-- command args]
  -h HOST | --host=HOST          Host or IP under test
  -p PORT | --port=PORT          TCP port under test
                                 Alternatively use host:port as a single argument
  -s | --strict                  Only execute subcommand if the test succeeds
  -q | --quiet                   Suppress all output except errors
  -t TIMEOUT | --timeout=TIMEOUT
                                 Timeout in seconds (0 = no timeout)
  -- COMMAND ARGS                Execute command after test
EOF
  exit 1
}

wait_for_connection() {
  [[ $TIMEOUT -gt 0 ]] && \
    echoerr "$CMD_NAME: waiting $TIMEOUT seconds for $HOST:$PORT" || \
    echoerr "$CMD_NAME: waiting for $HOST:$PORT without timeout"

  local start_ts=$(date +%s)
  local result

  while :; do
    if [[ $IS_BUSYBOX -eq 1 ]]; then
      nc -z $HOST $PORT
      result=$?
    else
      (echo -n > /dev/tcp/$HOST/$PORT) >/dev/null 2>&1
      result=$?
    fi

    if [[ $result -eq 0 ]]; then
      local end_ts=$(date +%s)
      echoerr "$CMD_NAME: $HOST:$PORT is available after $((end_ts - start_ts)) seconds"
      break
    fi
    sleep 1
  done

  return $result
}

wait_with_timeout() {
  if [[ $QUIET -eq 1 ]]; then
    timeout $BUSY_TIMEOUT_FLAG $TIMEOUT $0 --quiet --child --host=$HOST --port=$PORT --timeout=$TIMEOUT &
  else
    timeout $BUSY_TIMEOUT_FLAG $TIMEOUT $0 --child --host=$HOST --port=$PORT --timeout=$TIMEOUT &
  fi

  local pid=$!
  trap "kill -INT -$pid" INT
  wait $pid
  return $?
}

# Parse CLI arguments
while [[ $# -gt 0 ]]; do
  case "$1" in
    *:*)
      IFS=':' read HOST PORT <<<"$1"
      shift
      ;;
    -h) HOST="$2"; shift 2 ;;
    --host=*) HOST="${1#*=}"; shift ;;
    -p) PORT="$2"; shift 2 ;;
    --port=*) PORT="${1#*=}"; shift ;;
    -t) TIMEOUT="$2"; shift 2 ;;
    --timeout=*) TIMEOUT="${1#*=}"; shift ;;
    -q|--quiet) QUIET=1; shift ;;
    -s|--strict) STRICT=1; shift ;;
    --child) CHILD=1; shift ;;
    --) shift; CLI_CMD=("$@"); break ;;
    --help) usage ;;
    *) echoerr "Unknown argument: $1"; usage ;;
  esac
done

# Validate host and port
[[ -z "$HOST" || -z "$PORT" ]] && { echoerr "Error: host and port required"; usage; }

# Determine if `timeout` is from busybox
TIMEOUT_PATH=$(type -p timeout)
TIMEOUT_PATH=$(realpath "$TIMEOUT_PATH" 2>/dev/null || readlink -f "$TIMEOUT_PATH")

if [[ $TIMEOUT_PATH =~ busybox ]]; then
  IS_BUSYBOX=1
  timeout 2>&1 | grep -q -- "-t " && BUSY_TIMEOUT_FLAG="-t"
fi

# Main logic
if [[ $CHILD -eq 1 ]]; then
  wait_for_connection
  exit $?
else
  if [[ $TIMEOUT -gt 0 ]]; then
    wait_with_timeout
    RESULT=$?
  else
    wait_for_connection
    RESULT=$?
  fi
fi

# Run command if specified
if [[ ${#CLI_CMD[@]} -gt 0 ]]; then
  if [[ $RESULT -ne 0 && $STRICT -eq 1 ]]; then
    echoerr "$CMD_NAME: strict mode, refusing to execute command"
    exit $RESULT
  fi
  exec "${CLI_CMD[@]}"
else
  exit $RESULT
fi
