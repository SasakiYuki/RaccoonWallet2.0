package wallet.raccoon.raccoonwallet.type.event

import wallet.raccoon.raccoonwallet.model.db.WalletInfo

sealed class WalletInfoEvent {
  class InsertWalletInfo(val walletInfo: WalletInfo) : WalletInfoEvent()
  class UpdateWalletInfo(val walletInfo: WalletInfo) : WalletInfoEvent()
}
