package wallet.raccoon.raccoonwallet.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.MainActivity
import wallet.raccoon.raccoonwallet.di.activity.MainActivityModule
import wallet.raccoon.raccoonwallet.di.fragment.HomeModule

@Module
internal abstract class ActivityBuilderModule {
  @ActivityScope
  @ContributesAndroidInjector(modules = [(MainActivityModule::class), (HomeModule::class)])
  abstract fun bindMainActivity(): MainActivity
}