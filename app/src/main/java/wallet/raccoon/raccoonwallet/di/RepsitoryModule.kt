package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.Provides
import wallet.raccoon.raccoonwallet.repository.HarvestRepository
import wallet.raccoon.raccoonwallet.rest.service.HarvestService
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideHarvestRepository(harvestService: HarvestService) = HarvestRepository(harvestService)
}
