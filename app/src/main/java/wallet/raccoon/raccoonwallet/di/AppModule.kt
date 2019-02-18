package wallet.raccoon.raccoonwallet.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wallet.raccoon.raccoonwallet.RaccoonApplication
import wallet.raccoon.raccoonwallet.db.RaccoonDatabase
import wallet.raccoon.raccoonwallet.rest.ApiManager
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ServiceModule::class), (RepositoryModule::class), (ViewModelModule::class), (DatabaseModule::class)])
internal class AppModule {
  @Provides
  @Singleton
  fun provideContext(application: RaccoonApplication): Context = application

  @Provides
  @Singleton
  fun provideSharedPreferenceUtils(context: Context) = SharedPreferenceUtils(context)

  @Provides
  @Singleton
  fun provideRoomDatabase(context: Context) = Room.databaseBuilder(context, RaccoonDatabase::class.java, RaccoonDatabase.RACCOON_WALLET_DATABASE_NAME).build()

  @Provides
  @Singleton
  @Named(NORMAL)
  fun provideRetrofit(sharedPreferenceUtils: SharedPreferenceUtils): Retrofit {
    return ApiManager(sharedPreferenceUtils).builder()
  }

  @Provides
  @Singleton
  @Named(ZAIF)
  fun provideZaifRetrofit(sharedPreferenceUtils: SharedPreferenceUtils): Retrofit {
    return ApiManager(sharedPreferenceUtils).builderZaif()
  }

  @Provides
  @Singleton
  @Named(XEMBOOK)
  fun provideXembookRetrofit(sharedPreferenceUtils: SharedPreferenceUtils): Retrofit {
    return ApiManager(sharedPreferenceUtils).builderXembook()
  }

  companion object {
    const val NORMAL = "normal"
    const val ZAIF = "zaif"
    const val XEMBOOK = "xembook"
  }
}
