package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import wallet.raccoon.raccoonwallet.db.RaccoonDatabase
import wallet.raccoon.raccoonwallet.db.dao.WalletDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideWalletDao(database: RaccoonDatabase): WalletDao = database.walletDao()
}