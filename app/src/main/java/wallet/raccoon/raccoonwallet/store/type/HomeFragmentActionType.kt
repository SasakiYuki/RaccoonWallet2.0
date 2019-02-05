package wallet.raccoon.raccoonwallet.store.type

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair

sealed class HomeFragmentActionType {
    class AccountInfo(val accountMetaDataPair: AccountMetaDataPair) : HomeFragmentActionType()
    class AllTransaction(val transactionList: List<TransactionMetaDataPair>) : HomeFragmentActionType()
    class HarvestInfo(val harvestInfoLists: List<com.ryuta46.nemkotlin.model.HarvestInfo>) : HomeFragmentActionType()
}
