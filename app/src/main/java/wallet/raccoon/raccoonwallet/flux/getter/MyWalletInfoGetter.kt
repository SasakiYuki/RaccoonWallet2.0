package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.MyWalletInfoReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class MyWalletInfoGetter(reducer: MyWalletInfoReducer) : DisposableMapper() {
  val walletInfoObservable: Observable<WalletInfo> = reducer.walletInfoObservable
  val myAddressObservable: Observable<MyAddress> = reducer.myAddressObservable
  val deleteMyAddressObservable: Observable<Unit> = reducer.deleteMyAddressObservable
  val errorObservable: Observable<Throwable> = reducer.errorObservable
}
