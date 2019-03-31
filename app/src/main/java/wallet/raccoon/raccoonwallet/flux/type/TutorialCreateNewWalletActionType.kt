package wallet.raccoon.raccoonwallet.flux.type

sealed class TutorialCreateNewWalletActionType {
    class CreateAndInsertWallet(val address: String) : TutorialCreateNewWalletActionType()
}
