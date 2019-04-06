package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.TutorialLessonPrivateKeyDisplayActionType


class TutorialLessonPrivateKeyDisplayReducer(actionType: Observable<TutorialLessonPrivateKeyDisplayActionType>) :
    DisposableMapper() {

    private val mPrivateKey: PublishSubject<String> = PublishSubject.create()

    val privateKey: Observable<String>
        get() = mPrivateKey

    init {
        actionType.ofType(TutorialLessonPrivateKeyDisplayActionType.PrivateKey::class.java)
            .subscribe({
                mPrivateKey.onNext(it.privateKey)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }
    }
}
