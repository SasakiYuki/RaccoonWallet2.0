package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.WalletRepository
import javax.inject.Inject


class TutorialLessonPrivateKeyDisplayUseCase @Inject constructor(private val walletRepository: WalletRepository) {

    suspend fun decryptPrivateKey(walletId: Long, pin: ByteArray) = walletRepository.decryptPrivateKey(walletId, pin)
}
