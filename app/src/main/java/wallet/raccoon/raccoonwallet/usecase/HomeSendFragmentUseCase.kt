package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.AccountRepository
import javax.inject.Inject

class HomeSendFragmentUseCase @Inject constructor(
  private val accountRepository: AccountRepository
) {
  fun getAccountInfo(address: String) =
    accountRepository.getAccountInfo(address)
}
