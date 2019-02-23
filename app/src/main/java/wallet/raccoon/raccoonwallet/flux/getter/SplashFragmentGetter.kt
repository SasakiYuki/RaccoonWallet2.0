package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity
import wallet.raccoon.raccoonwallet.flux.reducer.SplashFragmentReducer

class SplashFragmentGetter(reducer: SplashFragmentReducer) : DisposableMapper() {
    val activeNode: Observable<ActiveNodeEntity> = reducer.activeNode
}