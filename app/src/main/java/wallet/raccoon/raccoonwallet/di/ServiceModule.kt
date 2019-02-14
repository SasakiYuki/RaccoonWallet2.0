package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wallet.raccoon.raccoonwallet.rest.service.AccountService
import wallet.raccoon.raccoonwallet.rest.service.XembookService
import wallet.raccoon.raccoonwallet.rest.service.ZaifService
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {
  @Provides
  @Singleton
  fun provideAccountService(@Named(AppModule.NORMAL) retrofit: Retrofit): AccountService =
    retrofit.create(AccountService::class.java)

  @Provides
  @Singleton
  fun provideZaifService(@Named(AppModule.ZAIF) retrofit: Retrofit): ZaifService =
    retrofit.create(ZaifService::class.java)

  @Provides
  @Singleton
  fun provideXembookService(@Named(AppModule.XEMBOOK) retrofit: Retrofit): XembookService =
    retrofit.create(XembookService::class.java)
}