package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.MyProfileRepository
import javax.inject.Inject

class MainActivityUseCase @Inject constructor(private val myProfileRepository: MyProfileRepository) {
  suspend fun loadMyProfile() = myProfileRepository.loadMyProfile()
}