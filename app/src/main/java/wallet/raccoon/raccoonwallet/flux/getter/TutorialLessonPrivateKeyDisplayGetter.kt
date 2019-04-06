package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.TutorialLessonPrivateKeyDisplayReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper


class TutorialLessonPrivateKeyDisplayGetter(reducer: TutorialLessonPrivateKeyDisplayReducer) : DisposableMapper() {
    val privateKey: Observable<String> = reducer.privateKey
}
