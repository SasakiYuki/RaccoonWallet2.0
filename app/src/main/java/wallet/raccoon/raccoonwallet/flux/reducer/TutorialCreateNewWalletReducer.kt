package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.TutorialCreateNewWalletActionType

class TutorialCreateNewWalletReducer(actionType: Observable<TutorialCreateNewWalletActionType>) : DisposableMapper() {
    private val mCreateAndInsertWallet: PublishSubject<String> = PublishSubject.create()

    val createAndInsertWallet: Observable<String>
        get() = mCreateAndInsertWallet

    init {
        actionType.ofType(TutorialCreateNewWalletActionType.CreateAndInsertWallet::class.java)
            .subscribe({
                mCreateAndInsertWallet.onNext(it.address)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }
    }
}
