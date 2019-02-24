package wallet.raccoon.raccoonwallet.store.type

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.model.db.Wallet

sealed class SelectWalletActivityActionType {
    class Wallets(val wallets: List<Wallet>) : SelectWalletActivityActionType()
    class SelectedWalletId(val selectWalletId: Long) : SelectWalletActivityActionType()
    class SaveSelectedWalletId(val complete: Unit) : SelectWalletActivityActionType()
    class UpdateWallet(val walletId: Long) : SelectWalletActivityActionType()
    class AccountInfo(val accountMetaDataPair: AccountMetaDataPair) : SelectWalletActivityActionType()
}