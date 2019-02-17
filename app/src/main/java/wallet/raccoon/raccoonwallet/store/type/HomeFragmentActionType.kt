package wallet.raccoon.raccoonwallet.store.type

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.model.rest.TransactionData
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity

sealed class HomeFragmentActionType {
  class AccountInfo(val accountMetaDataPair: AccountMetaDataPair) : HomeFragmentActionType()
  class AllTransaction(val transactionList: TransactionData) : HomeFragmentActionType()
  class HarvestInfo(val harvestInfoLists: HarvestInfos) : HomeFragmentActionType()
  class ZaifNemPrice(val zaifNemEntity: ZaifNemEntity) : HomeFragmentActionType()
  class WalletEntity(val wallet: Wallet) : HomeFragmentActionType()
}
