package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.activity.MainActivityModule
import wallet.raccoon.raccoonwallet.di.activity.MyAddressProfileActivityModule
import wallet.raccoon.raccoonwallet.di.activity.ProfileAddressAddActivityModule
import wallet.raccoon.raccoonwallet.di.activity.SelectWalletActivityModule
import wallet.raccoon.raccoonwallet.di.activity.SendActivityModule
import wallet.raccoon.raccoonwallet.di.fragment.AmountInputModule
import wallet.raccoon.raccoonwallet.di.fragment.HomeModule
import wallet.raccoon.raccoonwallet.di.fragment.HomeSendModule
import wallet.raccoon.raccoonwallet.di.fragment.MyProfileInfoModule
import wallet.raccoon.raccoonwallet.di.fragment.MyWalletInfoModule
import wallet.raccoon.raccoonwallet.di.fragment.SplashModule
import wallet.raccoon.raccoonwallet.view.activity.MainActivity
import wallet.raccoon.raccoonwallet.view.activity.SelectWalletActivity
import wallet.raccoon.raccoonwallet.view.activity.SendActivity
import wallet.raccoon.raccoonwallet.view.activity.profile.MyAddressProfileActivity
import wallet.raccoon.raccoonwallet.view.activity.profile.ProfileAddressAddActivity

@Module
internal abstract class ActivityBuilderModule {
  @ActivityScope
  @ContributesAndroidInjector(
      modules = [(MainActivityModule::class), (HomeModule::class), (SplashModule::class), (HomeSendModule::class)]
  )
  abstract fun bindMainActivity(): MainActivity

  @ActivityScope
  @ContributesAndroidInjector(
      modules = [(SendActivityModule::class), (AmountInputModule::class)]
  )
  abstract fun bindSendActivity(): SendActivity

  @ActivityScope
  @ContributesAndroidInjector(modules = [SelectWalletActivityModule::class])
  abstract fun bindSelectWalletActivity(): SelectWalletActivity

  @ActivityScope
  @ContributesAndroidInjector(
      modules = [(MyAddressProfileActivityModule::class), (MyProfileInfoModule::class), (MyWalletInfoModule::class)]
  )
  abstract fun bindMyAddressProfileActivity(): MyAddressProfileActivity

  @ActivityScope
  @ContributesAndroidInjector(
      modules = [ProfileAddressAddActivityModule::class]
  )
  abstract fun bindProfileAddressAddActivity(): ProfileAddressAddActivity
}