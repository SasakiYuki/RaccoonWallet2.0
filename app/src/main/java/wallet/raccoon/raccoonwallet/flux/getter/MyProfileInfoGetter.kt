package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.MyProfileInfoReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.MyProfileEntity

class MyProfileInfoGetter(reducer: MyProfileInfoReducer) : DisposableMapper() {
  val myAddressObservable: Observable<Int> = reducer.myAddressObservable
  val myProfileEntityObservable: Observable<MyProfileEntity> = reducer.myProfileEntityObservable
  val updateObservable: Observable<MyProfileEntity> = reducer.updateObservable
  val errorObservable: Observable<Throwable> = reducer.errorObservable
}
