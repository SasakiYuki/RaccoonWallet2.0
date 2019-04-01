package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.profile.MyProfileInfoFragment

@Module
internal abstract class MyProfileInfoModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = [(MyProfileInfoFragmentModule::class)])
  abstract fun bindMyProfileInfoFragmentInjectFactory(): MyProfileInfoFragment
}

@Module
class MyProfileInfoFragmentModule
