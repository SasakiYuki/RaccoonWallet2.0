package wallet.raccoon.raccoonwallet.store.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.type.SelectWalletActivityActionType

class SelectWalletActivityReducer(actionType: Observable<SelectWalletActivityActionType>) : DisposableMapper() {
    private val mWallets: PublishSubject<List<Wallet>> = PublishSubject.create()
    private val mSelectedWalletId: PublishSubject<Long> = PublishSubject.create()
    private val mSaveSelectedWalletId: PublishSubject<Unit> = PublishSubject.create()

    val wallets: Observable<List<Wallet>>
        get() = mWallets

    val selectedWalletId: Observable<Long>
        get() = mSelectedWalletId

    val saveSelectedWalletId: Observable<Unit>
        get() = mSaveSelectedWalletId

    init {
        actionType.ofType(SelectWalletActivityActionType.Wallets::class.java)
            .subscribe({
                mWallets.onNext(it.wallets)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }

        actionType.ofType(SelectWalletActivityActionType.SelectedWalletId::class.java)
            .subscribe({
                mSelectedWalletId.onNext(it.selectWalletId)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }

        actionType.ofType(SelectWalletActivityActionType.SaveSelectedWalletId::class.java)
            .subscribe({
                mSaveSelectedWalletId.onNext(it.complete)
            }, {
                it.printStackTrace()
            })
            .let { disposables.add(it) }
    }
}