package wallet.raccoon.raccoonwallet.util

import wallet.raccoon.raccoonwallet.model.db.Wallet


object WalletProvider {
    var wallet: Wallet? = null

    fun clearCache() {
        wallet = null
    }
}
