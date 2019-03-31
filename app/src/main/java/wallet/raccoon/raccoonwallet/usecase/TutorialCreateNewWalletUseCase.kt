package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.repository.AccountRepository
import wallet.raccoon.raccoonwallet.repository.WalletRepository
import javax.inject.Inject

class TutorialCreateNewWalletUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val walletRepository: WalletRepository
) {

    fun createWallet(walletName: String): Wallet {
        val account = accountRepository.createNewAccount()
        return walletRepository.createWallet(account, walletName)
    }

    suspend fun insertWallet(wallet: Wallet) = walletRepository.saveWallet(wallet)
}
