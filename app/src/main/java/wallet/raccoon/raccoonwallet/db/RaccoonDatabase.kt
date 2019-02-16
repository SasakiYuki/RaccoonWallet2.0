package wallet.raccoon.raccoonwallet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import wallet.raccoon.raccoonwallet.model.db.Wallet

@Database(entities = [Wallet::class], version = 1)
abstract class RaccoonDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao

    companion object {
        val RACCOON_WALLET_DATABASE_NAME = "raccoon_wallet.db"
    }
}
