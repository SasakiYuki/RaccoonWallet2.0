package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wallet.raccoon.raccoonwallet.rest.HarvestService
import javax.inject.Singleton

@Module
class ServiceModule {
  @Provides
  @Singleton
  fun provideHarvestService(retrofit: Retrofit): HarvestService =
    retrofit.create(HarvestService::class.java)
}