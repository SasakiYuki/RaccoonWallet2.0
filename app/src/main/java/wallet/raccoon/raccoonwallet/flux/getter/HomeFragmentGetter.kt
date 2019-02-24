package wallet.raccoon.raccoonwallet.flux.getter

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.model.rest.TransactionData
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity
import wallet.raccoon.raccoonwallet.flux.reducer.HomeFragmentReducer

class HomeFragmentGetter(reducer: HomeFragmentReducer) : DisposableMapper() {
  val accountInfo: Observable<AccountMetaDataPair> = reducer.accountInfo
  val transactionList: Observable<TransactionData> = reducer.transactionList
  val harvestList: Observable<HarvestInfos> = reducer.harvestInfoList
  val nemPrice: Observable<ZaifNemEntity> = reducer.nemPrice
  val wallet: Observable<Wallet> = reducer.wallet
}