package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope

@Module
internal abstract class HomeModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
  abstract fun bindHomeFragmentInjectFactory(): HomeFragmentModule
}

@Module
class HomeFragmentModule
