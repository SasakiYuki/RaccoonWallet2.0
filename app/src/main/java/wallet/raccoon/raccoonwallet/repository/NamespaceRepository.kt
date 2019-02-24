package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.service.NamespaceService
import javax.inject.Inject

class NamespaceRepository @Inject constructor(
  private val namespaceService: NamespaceService
){
  fun getNamespaceMosaicDefinitionPage(namespace:String) = namespaceService.namespaceMosaicDefinitionPage(namespace)
}