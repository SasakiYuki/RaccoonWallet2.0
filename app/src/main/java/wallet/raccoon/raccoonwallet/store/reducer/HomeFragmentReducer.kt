package wallet.raccoon.raccoonwallet.store.reducer

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.model.rest.TransactionData
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity
import wallet.raccoon.raccoonwallet.store.type.HomeFragmentActionType

class HomeFragmentReducer(actionType: Observable<HomeFragmentActionType>) : DisposableMapper() {
  private val mAccountInfo: PublishSubject<AccountMetaDataPair> = PublishSubject.create()
  private val mTransactionList: PublishSubject<TransactionData> = PublishSubject.create()
  private val mHarvestInfoList: PublishSubject<HarvestInfos> = PublishSubject.create()
  private val mNemPrice: PublishSubject<ZaifNemEntity> = PublishSubject.create()
  private val mWallet: PublishSubject<Wallet> = PublishSubject.create()

  val accountInfo: Observable<AccountMetaDataPair>
    get() = mAccountInfo
  val transactionList: Observable<TransactionData>
    get() = mTransactionList
  val harvestInfoList: Observable<HarvestInfos>
    get() = mHarvestInfoList
  val nemPrice: Observable<ZaifNemEntity>
    get() = mNemPrice
  val wallet: Observable<Wallet>
    get() = mWallet

  init {
    actionType.ofType(HomeFragmentActionType.AccountInfo::class.java)
        .subscribe({
          mAccountInfo.onNext(it.accountMetaDataPair)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    actionType.ofType(HomeFragmentActionType.AllTransaction::class.java)
        .subscribe({
          mTransactionList.onNext(it.transactionList)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    actionType.ofType(HomeFragmentActionType.HarvestInfo::class.java)
        .subscribe({
          mHarvestInfoList.onNext(it.harvestInfoLists)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    actionType.ofType(HomeFragmentActionType.ZaifNemPrice::class.java)
        .subscribe({
          mNemPrice.onNext(it.zaifNemEntity)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    actionType.ofType(HomeFragmentActionType.WalletEntity::class.java)
      .subscribe({
        mWallet.onNext(it.wallet)
      },{
        it.printStackTrace()
      })
      .let { disposables.add(it) }
  }
}