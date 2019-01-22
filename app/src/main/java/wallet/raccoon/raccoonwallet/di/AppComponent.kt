package wallet.raccoon.raccoonwallet.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import wallet.raccoon.raccoonwallet.RaccoonApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilderModule::class)])
internal interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RaccoonApplication): Builder

        fun build(): AppComponent
    }

    fun inject(instance: RaccoonApplication?)
}
