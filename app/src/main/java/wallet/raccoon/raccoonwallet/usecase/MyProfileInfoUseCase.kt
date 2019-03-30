package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.repository.MyAddressRepository
import wallet.raccoon.raccoonwallet.repository.MyProfileRepository
import javax.inject.Inject

class MyProfileInfoUseCase @Inject constructor(private val myAddressRepository: MyAddressRepository,
  private val myProfileRepository: MyProfileRepository
) {

  fun countAllMyAddress() = myAddressRepository.countAllMyAddress()

  fun loadMyProfile() = myProfileRepository.loadMyProfileSingle()

  fun updateMyProfile(entity: MyProfileEntity) = myProfileRepository.updateMyProfile(entity)
}
