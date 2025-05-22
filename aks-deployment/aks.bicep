param location string = resourceGroup().location
param clusterName string = 'aks-kashish'
param dnsPrefix string = 'aks-kashish-dns'
param clientId string
param clientSecret string
param tenantId string

resource aksCluster 'Microsoft.ContainerService/managedClusters@2023-01-01' = {
  name: clusterName
  location: location
  identity: {
    // to connect aks to any azure service
    type: 'SystemAssigned'
  }
  properties: {
    dnsPrefix: dnsPrefix
    agentPoolProfiles: [
      {
        name: 'nodepool1'
        count: 1
        vmSize: 'Standard_B2ms'
        osType: 'Linux'
        type: 'VirtualMachineScaleSets'
        mode: 'System'
      }
    ]
    linuxProfile: {
      adminUsername: 'azureuser'
      ssh: {
        publicKeys: [
          {
            keyData: 'ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQC1wmAbN9X8VadqYSqZNonqSiaEbnz6IYsYzOL/Ib2dpkCHbwvoeKQbi2by25yVKjqvB4jJglS25bQhStRmdzJBebG9eYLbv4r6EMZMLV3opU/BKjEHu4b8VmcKWGF8GGJtgewCIIT8wh6AQJK1T9+imOLz5iorQCdGhYFvNCxlg2YP9ClW6ggPMeHVcHRXRtNrA5Y71iMDEXw1jSqnLo+XMFKpSZwxBVZslO4A9Sp5tno4eDylqebwnzvOI/Melwl5nC2OS3QSxY8WhyrBpsfpbxV1DyTaSYAeWqCJBOrb5r6rQRdDNZdnqbFN3C3Ydg2x7DaUYseN5QdqmsckWkjCpTh+sp+hXcEGeqS+HuEFLWUHInygDsqMcABH5l2VnMsCAsfMKRw4xHSTLmmVXwfEf3W3vTaCFalPQ2wlhOHeoQF3+8jhBKDJMDWwTvFC6E/iHDhwzM8mgsqwsj3lVv99m1kbJN89rQQQCsd3Q8YWu3mjL9tBNuxCEr+MhBCGq+Sdl9eYkvwJ1zsIXS00kgVYtGISSst7ze2JEnMbUUr6X0/wfJZbBvcD2eN/2X98DD59YgBZtwB55QAMmJP9D1q+GFT5JySPqQjIQgrrmyTxgiolxOqMzBZVJcKGS337wxSA3ebYIFez07CpedFL8AKLZlT/Ie22B8oQjc5R9bLQTw== kashishrastogi@IN-PG04G7WN'
          }
        ]
      }
    }
    servicePrincipalProfile: {
      clientId: clientId
      secret: clientSecret
    }
    
    // kubernetesVersion: '1.27.3' 
    enableRBAC: true
  }
}
