package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.ActiveNodeRepository
import javax.inject.Inject

class SplashFragmentUseCase @Inject constructor(private val activeNodeRepository: ActiveNodeRepository) {
    fun getActiveNodeList() = activeNodeRepository.getActiveNodeList()
}