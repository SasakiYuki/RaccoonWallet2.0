package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.db.WalletInfo

sealed class ProfileAddressAddActionType {
  class Insert(val walletInfo: WalletInfo) : ProfileAddressAddActionType()
  class Update(val walletInfo: WalletInfo) : ProfileAddressAddActionType()
  class Error(val throwable: Throwable) : ProfileAddressAddActionType()
}
