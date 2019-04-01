package wallet.raccoon.raccoonwallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wallet.raccoon.raccoonwallet.viewmodel.HomeFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.HomeSendFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel
import wallet.raccoon.raccoonwallet.viewmodel.MyWalletInfoViewModel
import wallet.raccoon.raccoonwallet.viewmodel.OwnedMosaicSelectFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.SelectWalletViewModel
import wallet.raccoon.raccoonwallet.viewmodel.SplashFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.profile.MyAddressProfileViewModel
import wallet.raccoon.raccoonwallet.viewmodel.profile.MyProfileInfoViewModel
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
  @ViewModelKey(MyAddressProfileViewModel::class)
  abstract fun bindMyAddressProfileViewModel(viewModel: MyAddressProfileViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MyWalletInfoViewModel::class)
  abstract fun bindMyWalletInfoViewModel(viewModel: MyWalletInfoViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MyProfileInfoViewModel::class)
  abstract fun bindMyProfileInfoViewModel(viewModel: MyProfileInfoViewModel): ViewModel
}