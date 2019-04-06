package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialCreateNewWalletFragment


@Module
internal abstract class TutorialCreateNewWalletModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [(TutorialCreateNewWalletFragmentModule::class)])
    abstract fun bindTutorialCreateNewWalletFragmentInjectFactory(): TutorialCreateNewWalletFragment
}

@Module
class TutorialCreateNewWalletFragmentModule
