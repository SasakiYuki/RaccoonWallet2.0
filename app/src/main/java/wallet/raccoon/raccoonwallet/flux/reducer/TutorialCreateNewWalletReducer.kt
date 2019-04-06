package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.TutorialCreateNewWalletActionType
import wallet.raccoon.raccoonwallet.model.db.Wallet

class TutorialCreateNewWalletReducer(actionType: Observable<TutorialCreateNewWalletActionType>) : DisposableMapper() {
    private val mCreateAndInsertWallet: PublishSubject<Wallet> = PublishSubject.create()

    val createAndInsertWallet: Observable<Wallet>
        get() = mCreateAndInsertWallet

    init {
        actionType.ofType(TutorialCreateNewWalletActionType.CreateAndInsertWallet::class.java)
            .subscribe({
                mCreateAndInsertWallet.onNext(it.wallet)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }
    }
}
