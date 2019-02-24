package wallet.raccoon.raccoonwallet.store.getter

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.reducer.SelectWalletActivityReducer

class SelectWalletActivityGetter(reducer: SelectWalletActivityReducer) : DisposableMapper() {
    val wallets: Observable<List<Wallet>> = reducer.wallets
    val selectedWalletId: Observable<Long> = reducer.selectedWalletId
    val saveSelectedWalletId: Observable<Unit> = reducer.saveSelectedWalletId
    val accountInfo: Observable<AccountMetaDataPair> = reducer.accountInfo
    val updateWallet: Observable<Long> = reducer.updateWallet
}