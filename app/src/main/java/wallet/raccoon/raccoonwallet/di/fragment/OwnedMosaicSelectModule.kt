package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.send.OwnedMosaicSelectFragment

@Module
internal abstract class AmountInputModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(OwnedMosaicSelectFragmentModule::class)])
  abstract fun bindOwnedMosaicSelectInjectFactory(): OwnedMosaicSelectFragment
}

@Module
class OwnedMosaicSelectFragmentModule
