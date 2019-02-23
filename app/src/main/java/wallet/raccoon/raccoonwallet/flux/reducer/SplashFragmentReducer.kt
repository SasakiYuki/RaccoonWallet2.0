package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity
import wallet.raccoon.raccoonwallet.flux.type.SplashFragmentActionType

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