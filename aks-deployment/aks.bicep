{
  "$schema": "https://schema.management.azure.com/schemas/2019-04-01/deploymentTemplate.json#",
  "contentVersion": "1.0.0.0",
  "metadata": {
    "_generator": {
      "name": "bicep",
      "version": "0.35.1.17967",
      "templateHash": "14910587955340125585"
    }
  },
  "parameters": {
    "location": {
      "type": "string",
      "defaultValue": "[resourceGroup().location]"
    },
    "clusterName": {
      "type": "string",
      "defaultValue": "aks-kashish"
    },
    "dnsPrefix": {
      "type": "string",
      "defaultValue": "aks-kashish-dns"
    },
    "clientId": {
      "type": "string"
    },
    "clientSecret": {
      "type": "string"
    },
    "tenantId": {
      "type": "string"
    }
  },
  "resources": [
    {
      "type": "Microsoft.ContainerService/managedClusters",
      "apiVersion": "2023-01-01",
      "name": "[parameters('clusterName')]",
      "location": "[parameters('location')]",
      "identity": {
        "type": "SystemAssigned"
      },
      "properties": {
        "dnsPrefix": "[parameters('dnsPrefix')]",
        "agentPoolProfiles": [
          {
            "name": "nodepool1",
            "count": 1,
            "vmSize": "Standard_B2ms",
            "osType": "Linux",
            "type": "VirtualMachineScaleSets",
            "mode": "System"
          }
        ],
        "linuxProfile": {
          "adminUsername": "azureuser",
          "ssh": {
            "publicKeys": [
              {
                "keyData": "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQC1wmAbN9X8VadqYSqZNonqSiaEbnz6IYsYzOL/Ib2dpkCHbwvoeKQbi2by25yVKjqvB4jJglS25bQhStRmdzJBebG9eYLbv4r6EMZMLV3opU/BKjEHu4b8VmcKWGF8GGJtgewCIIT8wh6AQJK1T9+imOLz5iorQCdGhYFvNCxlg2YP9ClW6ggPMeHVcHRXRtNrA5Y71iMDEXw1jSqnLo+XMFKpSZwxBVZslO4A9Sp5tno4eDylqebwnzvOI/Melwl5nC2OS3QSxY8WhyrBpsfpbxV1DyTaSYAeWqCJBOrb5r6rQRdDNZdnqbFN3C3Ydg2x7DaUYseN5QdqmsckWkjCpTh+sp+hXcEGeqS+HuEFLWUHInygDsqMcABH5l2VnMsCAsfMKRw4xHSTLmmVXwfEf3W3vTaCFalPQ2wlhOHeoQF3+8jhBKDJMDWwTvFC6E/iHDhwzM8mgsqwsj3lVv99m1kbJN89rQQQCsd3Q8YWu3mjL9tBNuxCEr+MhBCGq+Sdl9eYkvwJ1zsIXS00kgVYtGISSst7ze2JEnMbUUr6X0/wfJZbBvcD2eN/2X98DD59YgBZtwB55QAMmJP9D1q+GFT5JySPqQjIQgrrmyTxgiolxOqMzBZVJcKGS337wxSA3ebYIFez07CpedFL8AKLZlT/Ie22B8oQjc5R9bLQTw== kashishrastogi@IN-PG04G7WN"
              }
            ]
          }
        },
        "servicePrincipalProfile": {
          "clientId": "[parameters('clientId')]",
          "secret": "[parameters('clientSecret')]"
        },
        "kubernetesVersion": "",
        "enableRBAC": true
      }
    }
  ]
}