package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import wallet.raccoon.raccoonwallet.db.RaccoonDatabase
import wallet.raccoon.raccoonwallet.db.dao.MyAddressDao
import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import wallet.raccoon.raccoonwallet.db.dao.WalletInfoDao
import javax.inject.Singleton

@Module
class DatabaseModule {
  @Provides
  @Singleton
  fun provideWalletDao(database: RaccoonDatabase): WalletDao = database.walletDao()

  @Provides
  @Singleton
  fun provideWalletInfoDao(database: RaccoonDatabase): WalletInfoDao = database.walletInfoDao()

  @Provides
  @Singleton
  fun provideMyAddressDao(database: RaccoonDatabase): MyAddressDao = database.myAddressDao()
}