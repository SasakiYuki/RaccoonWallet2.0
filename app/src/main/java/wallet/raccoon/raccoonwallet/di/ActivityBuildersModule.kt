package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.activity.MainActivityModule
import wallet.raccoon.raccoonwallet.di.activity.SelectWalletActivityModule
import wallet.raccoon.raccoonwallet.di.activity.SendActivityModule
import wallet.raccoon.raccoonwallet.di.activity.WalletCreateActivityModule
import wallet.raccoon.raccoonwallet.di.fragment.*
import wallet.raccoon.raccoonwallet.view.activity.MainActivity
import wallet.raccoon.raccoonwallet.view.activity.SelectWalletActivity
import wallet.raccoon.raccoonwallet.view.activity.SendActivity
import wallet.raccoon.raccoonwallet.view.activity.WalletCreateActivity

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
        modules = [(WalletCreateActivityModule::class), (TutorialCreateNewWalletModule::class)]
    )
    abstract fun bindWalletCreateActivity(): WalletCreateActivity
}
