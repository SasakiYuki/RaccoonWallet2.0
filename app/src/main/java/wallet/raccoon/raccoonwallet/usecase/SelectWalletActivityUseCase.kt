package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.WalletRepository
import javax.inject.Inject

class SelectWalletActivityUseCase @Inject constructor(private val walletRepository: WalletRepository) {
    suspend fun getAllWallet() = walletRepository.getAll()
}