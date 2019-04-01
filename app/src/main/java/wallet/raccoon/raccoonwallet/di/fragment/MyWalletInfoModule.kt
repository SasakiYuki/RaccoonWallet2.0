package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.profile.MyWalletInfoFragment

@Module
internal abstract class MyWalletInfoModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(MyWalletInfoFragmentModule::class)])
  abstract fun bindMyWalletInfoFragmentInjectFactory(): MyWalletInfoFragment
}

@Module
class MyWalletInfoFragmentModule
