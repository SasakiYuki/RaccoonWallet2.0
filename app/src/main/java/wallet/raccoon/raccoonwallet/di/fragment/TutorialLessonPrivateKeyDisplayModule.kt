package wallet.raccoon.raccoonwallet.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wallet.raccoon.raccoonwallet.di.FragmentScope
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialLessonPrivateKeyDisplayFragment


@Module
internal abstract class TutorialLessonPrivateKeyDisplayModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [(TutorialLessonPrivateKeyDisplayFragmentModule::class)])
    abstract fun bindTutorialLessonPrivateKeyDisplayFragmentInjectFactory(): TutorialLessonPrivateKeyDisplayFragment
}

@Module
class TutorialLessonPrivateKeyDisplayFragmentModule
