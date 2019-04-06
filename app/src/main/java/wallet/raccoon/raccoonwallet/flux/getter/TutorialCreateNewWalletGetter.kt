package wallet.raccoon.raccoonwallet.flux.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.reducer.TutorialCreateNewWalletReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet

class TutorialCreateNewWalletGetter(reducer: TutorialCreateNewWalletReducer) : DisposableMapper() {
    val createAndInsertWallet: Observable<Wallet> = reducer.createAndInsertWallet
}
