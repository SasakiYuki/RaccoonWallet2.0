package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.SplashFragment

@Module
internal abstract class SplashModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [(SplashFragmentModule::class)])
    abstract fun bindSplashFragmentInjectFactory(): SplashFragment
}

@Module
class SplashFragmentModule