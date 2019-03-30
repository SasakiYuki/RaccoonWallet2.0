package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.repository.MyAddressRepository
import wallet.raccoon.raccoonwallet.repository.WalletInfoRepository
import javax.inject.Inject

class MyAddressProfileUseCase @Inject constructor(private val myAddressRepository: MyAddressRepository,
  private val walletInfoRepository: WalletInfoRepository
) {
  fun insertMyAddress(myAddress: MyAddress) = myAddressRepository.insert(myAddress)

  fun insertWalletInfo(walletInfo: WalletInfo) = walletInfoRepository.insert(walletInfo)
}
