package wallet.raccoon.raccoonwallet.store.getter

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.HarvestInfo
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.model.rest.TransactionData
import wallet.raccoon.raccoonwallet.store.reducer.HomeFragmentReducer

class HomeFragmentGetter(reducer: HomeFragmentReducer) : DisposableMapper() {
  val accountInfo: Observable<AccountMetaDataPair> = reducer.accountInfo
  val transactionList: Observable<TransactionData> = reducer.transactionList
  val harvestList: Observable<HarvestInfos> = reducer.harvestInfoList
}