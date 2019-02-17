package wallet.raccoon.raccoonwallet.store.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity
import wallet.raccoon.raccoonwallet.store.type.SplashFragmentActionType

class SplashFragmentReducer(actionType: Observable<SplashFragmentActionType>) : DisposableMapper() {
    private val mActiveNode: PublishSubject<ActiveNodeEntity> = PublishSubject.create()

    val activeNode: Observable<ActiveNodeEntity>
        get() = mActiveNode

    init {
        actionType.ofType(SplashFragmentActionType.ActiveNode::class.java)
            .subscribe({
                mActiveNode.onNext(it.activeNodeEntity)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }
    }
}