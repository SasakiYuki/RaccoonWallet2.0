package wallet.raccoon.raccoonwallet.store.type

import wallet.raccoon.raccoonwallet.model.db.Wallet

sealed class SelectWalletActivityActionType {
    class Wallets(val wallets: List<Wallet>) : SelectWalletActivityActionType()
}