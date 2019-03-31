package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.TutorialCreateNewWalletReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper

class TutorialCreateNewWalletGetter(reducer: TutorialCreateNewWalletReducer) : DisposableMapper() {
    val createAndInsertWallet: Observable<String> = reducer.createAndInsertWallet
}
