package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.HomeFragment

@Module
internal abstract class HomeModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
  abstract fun bindHomeFragmentInjectFactory(): HomeFragment
}

@Module
class HomeFragmentModule