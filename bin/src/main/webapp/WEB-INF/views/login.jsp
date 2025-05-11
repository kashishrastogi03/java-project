<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        /* Your same CSS styles as before */
        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            transition: 0.3s ease-in-out;
        }

        body {
            background-color: #bdf0eb;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 400px;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);
            text-align: center;
            transition: transform 0.3s ease-in-out;
        }

        .container:hover {
            transform: scale(1.02);
            box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
        }

        .container h2 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        .button-group {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .button-group button {
            width: 45%;
            padding: 12px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        .user-btn {
            background-color: #007bff;
            color: white;
        }

        .user-btn:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }

        .admin-btn {
            background-color: #28a745;
            color: white;
        }

        .admin-btn:hover {
            background-color: #1e7e34;
            transform: scale(1.05);
        }

        .login-form {
            display: none;
            text-align: left;
        }

        .login-form h3 {
            text-align: center;
            font-size: 20px;
            margin-bottom: 10px;
            color: #444;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            background: #f9f9f9;
        }

        input:focus, select:focus {
            background: #fff;
            border-color: #007bff;
            box-shadow: 0px 0px 5px rgba(0, 123, 255, 0.5);
        }

        .login-btn {
            width: 100%;
            background-color: #343a40;
            color: white;
            padding: 12px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 10px;
        }

        .login-btn:hover {
            background-color: #23272b;
            transform: scale(1.05);
        }

        .register-link {
            text-align: center;
            margin-top: 10px;
        }

        .register-link a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>LOGIN</h1>
    <br>

    <div class="button-group">
        <button class="user-btn" onclick="showLoginForm('user')">User Login</button>
    </div>

    <!-- User Login Form -->
    <form id="userLoginForm" class="login-form" action="${pageContext.request.contextPath}/auth/login/user" method="post">
        <h3>User Login</h3>
        <input type="text" name="username" placeholder="Enter Username" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <button type="submit" class="login-btn">Login</button>

        <div class="register-link">
            <p>New User? <a href="#" onclick="showLoginForm('register')">Register Here</a></p>
        </div>
    </form>


    <!-- User Registration Form -->
    <form id="registerForm" class="login-form" action="${pageContext.request.contextPath}/auth/register" method="post">
        <h3>User Registration</h3>
        <input type="text" name="username" placeholder="Enter Username" required>
        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
        <button type="submit" class="login-btn">Register</button>
    </form>



<!-- JavaScript to Toggle Form Views -->
<script>
    function showLoginForm(type) {
        document.getElementById('userLoginForm').style.display = (type === 'user') ? 'block' : 'none';
        document.getElementById('registerForm').style.display = (type === 'register') ? 'block' : 'none';
    }
</script>

</body>
</html>
