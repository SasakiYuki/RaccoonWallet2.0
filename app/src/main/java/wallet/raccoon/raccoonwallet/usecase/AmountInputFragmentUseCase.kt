package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.AccountRepository
import wallet.raccoon.raccoonwallet.repository.NamespaceRepository
import javax.inject.Inject

class AmountInputFragmentUseCase @Inject constructor(
  private val accountRepository: AccountRepository,
  private val namespaceRepository: NamespaceRepository
) {
  fun getOwnedMosaics(address: String) = accountRepository.getAccountMosaicOwned(address)

  fun getNamespaceMosaic(namespace: String) =
    namespaceRepository.getNamespaceMosaicDefinitionPage(namespace)
}