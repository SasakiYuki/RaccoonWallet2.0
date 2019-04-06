package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.db.Wallet

sealed class TutorialCreateNewWalletActionType {
    class CreateAndInsertWallet(val wallet: Wallet) : TutorialCreateNewWalletActionType()
}
