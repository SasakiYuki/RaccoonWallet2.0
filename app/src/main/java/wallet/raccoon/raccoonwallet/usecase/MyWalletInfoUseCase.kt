package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.repository.MyAddressRepository
import wallet.raccoon.raccoonwallet.repository.WalletInfoRepository
import javax.inject.Inject

class MyWalletInfoUseCase @Inject constructor(
  private val myAddressRepository: MyAddressRepository,
  private val walletInfoRepository: WalletInfoRepository
) {
  fun findAllMyAddress() = myAddressRepository.findAllMyAddress()

  fun select(id: Long) = walletInfoRepository.select(id)

  fun deleteMyAddress(myAddress: MyAddress) = myAddressRepository.delete(myAddress)
}
