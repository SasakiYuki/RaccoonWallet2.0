package wallet.raccoon.raccoonwallet.flux.type

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfoEntity
import wallet.raccoon.raccoonwallet.model.rest.TransactionEntity
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity

sealed class HomeFragmentActionType {
  class AccountInfo(val accountMetaDataPair: AccountMetaDataPair) : HomeFragmentActionType()
  class AllTransaction(val transactionList: TransactionEntity) : HomeFragmentActionType()
  class HarvestInfo(val harvestInfoLists: HarvestInfoEntity) : HomeFragmentActionType()
  class ZaifNemPrice(val zaifNemEntity: ZaifNemEntity) : HomeFragmentActionType()
  class WalletEntity(val wallet: Wallet) : HomeFragmentActionType()
}
