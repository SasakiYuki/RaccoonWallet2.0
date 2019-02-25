package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.send.AmountInputFragment

@Module
internal abstract class AmountInputModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(AmountInputFragmentModule::class)])
  abstract fun bindHomeFragmentInjectFactory(): AmountInputFragment
}

@Module
class AmountInputFragmentModule
