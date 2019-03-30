package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

sealed class MyWalletInfoActionType {
  class SelectWalletInfo(val walletInfo: WalletInfo) : MyWalletInfoActionType()
  class ReceiveMyAddress(val myAddress: MyAddress) : MyWalletInfoActionType()
  class DeleteMyAddress : MyWalletInfoActionType()
  class Error(val throwable: Throwable) : MyWalletInfoActionType()
}
