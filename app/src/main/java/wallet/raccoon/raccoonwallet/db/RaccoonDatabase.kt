package wallet.raccoon.raccoonwallet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import wallet.raccoon.raccoonwallet.db.dao.MyAddressDao
import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import wallet.raccoon.raccoonwallet.db.dao.WalletInfoDao
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

@Database(entities = [Wallet::class, WalletInfo::class, MyAddress::class], version = 1)
abstract class RaccoonDatabase : RoomDatabase() {
  abstract fun walletDao(): WalletDao
  abstract fun walletInfoDao(): WalletInfoDao
  abstract fun myAddressDao(): MyAddressDao

  companion object {
    const val RACCOON_WALLET_DATABASE_NAME = "raccoon_wallet.db"
  }
}
