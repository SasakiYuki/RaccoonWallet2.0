package wallet.raccoon.raccoonwallet.store.reducer

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.HarvestInfo
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.store.type.HomeFragmentActionType

class HomeFragmentReducer(actionType: Observable<HomeFragmentActionType>) : DisposableMapper() {
    private val mAccountInfo: PublishSubject<AccountMetaDataPair> = PublishSubject.create()
    private val mTransactionList: PublishSubject<List<TransactionMetaDataPair>> = PublishSubject.create()
    private val mHarvestInfoList: PublishSubject<List<HarvestInfo>> = PublishSubject.create()

    val accountInfo: Observable<AccountMetaDataPair>
        get() = mAccountInfo
    val transactionList: Observable<List<TransactionMetaDataPair>>
        get() = mTransactionList
    val harvestInfoList: Observable<List<HarvestInfo>>
        get() = mHarvestInfoList

    init {
        actionType.ofType(HomeFragmentActionType.AccountInfo::class.java)
            .subscribe({
                mAccountInfo.onNext(it.accountMetaDataPair)
            }, {
                it.printStackTrace()
            }).let { disposables.add(it) }

        actionType.ofType(HomeFragmentActionType.AllTransaction::class.java)
            .subscribe({
                mTransactionList.onNext(it.transactionList)
            }, {
                it.printStackTrace()
            }).let { disposables.add(it) }

        actionType.ofType(HomeFragmentActionType.HarvestInfo::class.java)
            .subscribe({
                mHarvestInfoList.onNext(it.harvestInfoLists)
            }, {
                it.printStackTrace()
            }).let { disposables.add(it) }
    }
}