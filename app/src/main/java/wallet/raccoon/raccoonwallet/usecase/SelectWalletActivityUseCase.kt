package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.WalletRepository
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import javax.inject.Inject

class SelectWalletActivityUseCase @Inject constructor(
    private val walletRepository: WalletRepository,
    private val sharedPreferenceUtils: SharedPreferenceUtils
) {
    suspend fun getAllWallet() = walletRepository.getAll()

    fun getSelectedWalletId() = sharedPreferenceUtils.selectedWalletId

    fun saveSelectedWalletId(selectedWalletId: Long) {
        sharedPreferenceUtils.selectedWalletId = selectedWalletId
    }
}