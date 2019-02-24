package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.home.HomeSendFragment

@Module
internal abstract class HomeSendModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(HomeSendFragmentModule::class)])
  abstract fun bindHomeSendFragmentInjectFactory(): HomeSendFragment
}

@Module
class HomeSendFragmentModule
