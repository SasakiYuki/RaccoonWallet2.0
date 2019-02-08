package wallet.raccoon.raccoonwallet.store.type

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos

sealed class HomeFragmentActionType {
    class AccountInfo(val accountMetaDataPair: AccountMetaDataPair) : HomeFragmentActionType()
    class AllTransaction(val transactionList: List<TransactionMetaDataPair>) : HomeFragmentActionType()
    class HarvestInfo(val harvestInfoLists: HarvestInfos) : HomeFragmentActionType()
}
