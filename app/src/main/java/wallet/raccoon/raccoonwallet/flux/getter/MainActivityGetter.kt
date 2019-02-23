package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.flux.reducer.MainActivityReducer

class MainActivityGetter(reducer: MainActivityReducer) : DisposableMapper() {
    val myProfile: Observable<MyProfileEntity> = reducer.myProfile
}