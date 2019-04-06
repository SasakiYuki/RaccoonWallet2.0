package wallet.raccoon.raccoonwallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wallet.raccoon.raccoonwallet.viewmodel.*
import wallet.raccoon.raccoonwallet.viewmodel.send.SendActivityViewModel

@Module
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun bindSplashFragmentViewModel(viewModel: SplashFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeSendFragmentViewModel::class)
    abstract fun bindHomeSendFragmentViewModel(viewModel: HomeSendFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OwnedMosaicSelectFragmentViewModel::class)
    abstract fun bindOwnedMosaicSelectFragmentViewModel(viewModel: OwnedMosaicSelectFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SendActivityViewModel::class)
    abstract fun bindSendActivityViewModel(viewModel: SendActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectWalletViewModel::class)
    abstract fun bindSelectWalletActivityViewModel(viewModel: SelectWalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TutorialCreateNewWalletViewModel::class)
    abstract fun bindTutorialCreateNewWalletFragmentViewModel(viewModel: TutorialCreateNewWalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TutorialLessonPrivateKeyDisplayViewModel::class)
    abstract fun bindTutorialLessonPrivateKeyDisplayFragmentViewModel(viewModel: TutorialLessonPrivateKeyDisplayViewModel): ViewModel
}
