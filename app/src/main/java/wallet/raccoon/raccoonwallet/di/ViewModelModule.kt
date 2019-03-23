package wallet.raccoon.raccoonwallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wallet.raccoon.raccoonwallet.viewmodel.OwnedMosaicSelectFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.HomeFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.HomeSendFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel
import wallet.raccoon.raccoonwallet.viewmodel.SplashFragmentViewModel
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
}