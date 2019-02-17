package wallet.raccoon.raccoonwallet.store.getter

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.reducer.SelectWalletActivityReducer

class SelectWalletActivityGetter(reducer: SelectWalletActivityReducer) : DisposableMapper() {
    val wallets: Observable<List<Wallet>> = reducer.wallets
}