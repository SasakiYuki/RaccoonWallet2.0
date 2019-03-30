package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.MyWalletInfoActionType
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class MyWalletInfoReducer(actionType: Observable<MyWalletInfoActionType>) : DisposableMapper() {
  private val mWalletInfoSubject: PublishSubject<WalletInfo> = PublishSubject.create()
  private val mMyAddressSubject: PublishSubject<MyAddress> = PublishSubject.create()
  private val mDeleteMyAddress: PublishSubject<Unit> = PublishSubject.create()
  private val mErrorSubject: PublishSubject<Throwable> = PublishSubject.create()

  val walletInfoObservable: Observable<WalletInfo>
    get() = mWalletInfoSubject
  val myAddressObservable: Observable<MyAddress>
    get() = mMyAddressSubject
  val deleteMyAddressObservable: Observable<Unit>
    get() = mDeleteMyAddress
  val errorObservable: Observable<Throwable>
    get() = mErrorSubject

  init {
    actionType.ofType(MyWalletInfoActionType.SelectWalletInfo::class.java)
        .subscribe({
          mWalletInfoSubject.onNext(it.walletInfo)
        }, {
          it.printStackTrace()
        }).let { disposables.add(it) }

    actionType.ofType(MyWalletInfoActionType.ReceiveMyAddress::class.java)
        .subscribe({
          mMyAddressSubject.onNext(it.myAddress)
        }, {
          it.printStackTrace()
        }).let { disposables.add(it) }

    actionType.ofType(MyWalletInfoActionType.DeleteMyAddress::class.java)
        .subscribe({
          mDeleteMyAddress.onNext(Unit)
        }, {
          it.printStackTrace()
        }).let { disposables.add(it) }

    actionType.ofType(MyWalletInfoActionType.Error::class.java)
        .subscribe({
          mErrorSubject.onNext(it.throwable)
        }, {
          it.printStackTrace()
        }).let { disposables.add(it) }
  }
}
