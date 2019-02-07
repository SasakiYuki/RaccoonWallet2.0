package wallet.raccoon.raccoonwallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wallet.raccoon.raccoonwallet.viewmodel.HomeFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel

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

}