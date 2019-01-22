package wallet.raccoon.raccoonwallet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import wallet.raccoon.raccoonwallet.RaccoonApplication
import javax.inject.Singleton

@Module(includes = [(ServiceModule::class), (RepositoryModule::class), (ViewModelModule::class)])
internal class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: RaccoonApplication): Context = application
}
