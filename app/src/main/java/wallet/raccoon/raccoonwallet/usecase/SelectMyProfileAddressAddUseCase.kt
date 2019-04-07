package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.MyAddressRepository
import wallet.raccoon.raccoonwallet.repository.WalletInfoRepository
import wallet.raccoon.raccoonwallet.repository.WalletRepository
import javax.inject.Inject

class SelectMyProfileAddressAddUseCase @Inject constructor(
  private val walletRepository: WalletRepository,
  private val myAddressRepository: MyAddressRepository,
  private val walletInfoRepository: WalletInfoRepository
) {

  suspend fun findAllWallet() = walletRepository.getAll()

  fun findAllMyAddress() = myAddressRepository.findAllMyAddress()

  fun select(id: Long) = walletInfoRepository.select(id)
}
