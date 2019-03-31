package wallet.raccoon.raccoonwallet.repository

import com.ryuta46.nemkotlin.account.Account
import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.util.AesCryptographer
import wallet.raccoon.raccoonwallet.util.KeyProvider
import wallet.raccoon.raccoonwallet.util.PinCodeProvider
import javax.inject.Inject

class WalletRepository @Inject constructor(private val walletDao: WalletDao) {
    suspend fun saveWallet(wallet: Wallet) = walletDao.insertOrUpdate(wallet)

    suspend fun getWalletById(walletId: Long) = walletDao.findById(walletId)

    suspend fun getAll() = walletDao.findAll()

    fun createWallet(account: Account, walletName: String): Wallet {
        val salt = KeyProvider.generateSalt()
        val pinCode = PinCodeProvider.getPinCode()
        val secretKey = KeyProvider.deriveKey(pinCode.toString(Charsets.UTF_8), salt)
        val encryptedSecretKey =
            AesCryptographer.encrypt(account.walletCompatiblePrivateKey.toByteArray(Charsets.UTF_8), secretKey)

        return Wallet(
            name = walletName,
            address = account.address,
            publicKey = account.publicKeyString,
            salt = salt,
            encryptedSecretKey = encryptedSecretKey
        )
    }
}
