package wallet.raccoon.raccoonwallet.store.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.type.SelectWalletActivityActionType

class SelectWalletActivityReducer(actionType: Observable<SelectWalletActivityActionType>) : DisposableMapper() {
    private val mWallets: PublishSubject<List<Wallet>> = PublishSubject.create()

    val wallets: Observable<List<Wallet>>
        get() = mWallets

    init {
//        actionType.ofType(MainActivityActionType.MyProfile::class.java)
//            .subscribe({
//                mMyProfile.onNext(it.myProfileEntity)
//            }, {
//                it.printStackTrace()
//            }).let { disposables.add(it) }
    }
}