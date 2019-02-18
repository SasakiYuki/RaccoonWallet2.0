package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.*
import javax.inject.Inject

class HomeFragmentUseCase @Inject constructor(
  private val harvestRepository: HarvestRepository,
  private val transactionRepository: TransactionRepository,
  private val accountRepository: AccountRepository,
  private val zaifRepository: ZaifRepository,
  private val walletRepository: WalletRepository
) {
  fun getHarvestInfo(address: String) = harvestRepository.getHarvestInfo(address)

  fun getAccountTransfersAll(address: String) =
    transactionRepository.getAccountTransfersAll(address)

  fun getAccountInfo(address: String) =
    accountRepository.getAccountInfo(address)

  fun getNemPrice() = zaifRepository.getNemPrice()

  suspend fun getWallet(walletId: Long) = walletRepository.getWalletById(walletId)
}