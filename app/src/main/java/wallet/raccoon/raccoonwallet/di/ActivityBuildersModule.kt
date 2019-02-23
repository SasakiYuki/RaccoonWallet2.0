package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.view.activity.MainActivity
import wallet.raccoon.raccoonwallet.di.activity.MainActivityModule
import wallet.raccoon.raccoonwallet.di.fragment.HomeModule
import wallet.raccoon.raccoonwallet.di.fragment.HomeSendModule
import wallet.raccoon.raccoonwallet.di.fragment.SplashModule

@Module
internal abstract class ActivityBuilderModule {
  @ActivityScope
  @ContributesAndroidInjector(
      modules = [(MainActivityModule::class), (HomeModule::class), (SplashModule::class), (HomeSendModule::class)]
  )
  abstract fun bindMainActivity(): MainActivity
}