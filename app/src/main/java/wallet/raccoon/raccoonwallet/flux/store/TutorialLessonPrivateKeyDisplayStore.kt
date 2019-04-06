package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.TutorialLessonPrivateKeyDisplayActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.TutorialLessonPrivateKeyDisplayGetter
import wallet.raccoon.raccoonwallet.flux.reducer.TutorialLessonPrivateKeyDisplayReducer
import wallet.raccoon.raccoonwallet.flux.type.TutorialLessonPrivateKeyDisplayActionType
import wallet.raccoon.raccoonwallet.usecase.TutorialLessonPrivateKeyDisplayUseCase
import javax.inject.Inject


class TutorialLessonPrivateKeyDisplayStore @Inject constructor(private val useCase: TutorialLessonPrivateKeyDisplayUseCase) :
    Store<TutorialLessonPrivateKeyDisplayActionType,
            TutorialLessonPrivateKeyDisplayActionCreator,
            TutorialLessonPrivateKeyDisplayReducer,
            TutorialLessonPrivateKeyDisplayGetter>() {

    override fun createActionCreator(
        dispatch: (TutorialLessonPrivateKeyDisplayActionType) -> Unit,
        reducer: TutorialLessonPrivateKeyDisplayReducer
    ): TutorialLessonPrivateKeyDisplayActionCreator {
        return TutorialLessonPrivateKeyDisplayActionCreator(useCase, dispatch)
    }

    override fun createReducer(action: Observable<TutorialLessonPrivateKeyDisplayActionType>): TutorialLessonPrivateKeyDisplayReducer {
        return TutorialLessonPrivateKeyDisplayReducer(action)
    }

    override fun createGetter(reducer: TutorialLessonPrivateKeyDisplayReducer): TutorialLessonPrivateKeyDisplayGetter {
        return TutorialLessonPrivateKeyDisplayGetter(reducer)
    }
}
