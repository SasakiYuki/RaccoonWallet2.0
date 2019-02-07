package wallet.raccoon.raccoonwallet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wallet.raccoon.raccoonwallet.RaccoonApplication
import wallet.raccoon.raccoonwallet.rest.ApiManager
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import javax.inject.Singleton

@Module(includes = [(ServiceModule::class), (RepositoryModule::class), (ViewModelModule::class)])
internal class AppModule {
  @Provides
  @Singleton
  fun provideContext(application: RaccoonApplication): Context = application

  @Provides
  @Singleton
  fun provideSharedPreferenceUtils(context: Context) = SharedPreferenceUtils(context)

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return ApiManager.builder()
  }
}
