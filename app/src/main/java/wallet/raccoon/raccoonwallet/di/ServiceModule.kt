package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wallet.raccoon.raccoonwallet.rest.AccountService
import javax.inject.Singleton

@Module
class ServiceModule {
  @Provides
  @Singleton
  fun provideAccountService(retrofit: Retrofit): AccountService =
    retrofit.create(AccountService::class.java)
}