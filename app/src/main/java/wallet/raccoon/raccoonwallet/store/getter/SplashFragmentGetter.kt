package wallet.raccoon.raccoonwallet.store.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity
import wallet.raccoon.raccoonwallet.store.reducer.SplashFragmentReducer

class SplashFragmentGetter(reducer: SplashFragmentReducer) : DisposableMapper() {
    val activeNode: Observable<ActiveNodeEntity> = reducer.activeNode
}