package wallet.raccoon.raccoonwallet.store.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.store.reducer.MainActivityReducer

class MainActivityGetter(reducer: MainActivityReducer) :DisposableMapper() {
    val myProfile: Observable<MyProfileEntity> = reducer.myProfile
}