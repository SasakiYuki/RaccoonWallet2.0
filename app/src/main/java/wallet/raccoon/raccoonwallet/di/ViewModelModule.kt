package wallet.raccoon.raccoonwallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wallet.raccoon.raccoonwallet.viewmodel.*

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
  @ViewModelKey(SelectWalletViewModel::class)
  abstract fun bindSelectWalletActivityViewModel(viewModel: SelectWalletViewModel): ViewModel
}