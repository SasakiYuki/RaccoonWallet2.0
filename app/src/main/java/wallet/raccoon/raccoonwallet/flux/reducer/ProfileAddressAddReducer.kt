package wallet.raccoon.raccoonwallet.flux.reducer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.ProfileAddressAddActionType
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class ProfileAddressAddReducer(action: Observable<ProfileAddressAddActionType>) : DisposableMapper() {
  private val mInsertSubject: PublishSubject<WalletInfo> = PublishSubject.create()
  private val mUpdateSubject: PublishSubject<WalletInfo> = PublishSubject.create()
  private val mErrorSubject: PublishSubject<Throwable> = PublishSubject.create()

  val insertObservable: Observable<WalletInfo>
    get() = mInsertSubject
  val updateObservable: Observable<WalletInfo>
    get() = mUpdateSubject
  val errorObservable: Observable<Throwable>
    get() = mErrorSubject

  init {
    action.ofType(ProfileAddressAddActionType.Insert::class.java)
        .subscribe({
          mInsertSubject.onNext(it.walletInfo)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    action.ofType(ProfileAddressAddActionType.Update::class.java)
        .subscribe({
          mUpdateSubject.onNext(it.walletInfo)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    action.ofType(ProfileAddressAddActionType.Error::class.java)
        .subscribe({
          mErrorSubject.onNext(it.throwable)
        })
        .let { disposables.add(it) }
  }
}
