package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.db.WalletInfo

sealed class MyAddressProfileActionType {
  class InsertMyAddress : MyAddressProfileActionType()
  class InsertWalletInfo(val walletInfo: WalletInfo) : MyAddressProfileActionType()
}
