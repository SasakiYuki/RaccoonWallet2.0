package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.ProfileAddressAddReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class ProfileAddressAddGetter(reducer: ProfileAddressAddReducer) : DisposableMapper() {
  val insertObservable: Observable<WalletInfo> = reducer.insertObservable
  val updateObservable: Observable<WalletInfo> = reducer.updateObservable
  val errorObservable: Observable<Throwable> = reducer.errorObservable
}
