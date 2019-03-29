package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.MyAddressProfileReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class MyAddressProfileGetter(reducer: MyAddressProfileReducer) : DisposableMapper() {
  val insertMyAddressObservable: Observable<Unit> = reducer.insertMyAddressObservable
  val insertWalletInfoObservable: Observable<WalletInfo> = reducer.insertWalletInfoObservable
}
