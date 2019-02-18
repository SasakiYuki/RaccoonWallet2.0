package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import wallet.raccoon.raccoonwallet.model.db.Wallet
import javax.inject.Inject

class WalletRepository @Inject constructor(private val walletDao: WalletDao) {
    suspend fun saveWallet(wallet: Wallet) = walletDao.insertOrUpdate(wallet)

    suspend fun getWalletById(walletId: Long) = walletDao.findById(walletId)
}