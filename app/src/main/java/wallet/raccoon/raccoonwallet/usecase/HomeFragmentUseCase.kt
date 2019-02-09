package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.AccountRepository
import wallet.raccoon.raccoonwallet.repository.HarvestRepository
import wallet.raccoon.raccoonwallet.repository.TransactionRepository
import javax.inject.Inject

class HomeFragmentUseCase @Inject constructor(
  private val harvestRepository: HarvestRepository,
  private val transactionRepository: TransactionRepository,
  private val accountRepository: AccountRepository
) {
  fun getHarvestInfo(address: String) = harvestRepository.getHarvestInfo(address)

  fun getAccountTransfersAll(address: String) =
    transactionRepository.getAccountTransfersAll(address)

  fun getAccountInfo(address: String) =
    accountRepository.getAccountInfo(address)
}