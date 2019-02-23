package wallet.raccoon.raccoonwallet.store.reducer

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.store.type.HomeSendFragmentActionType

class HomeSendFragmentReducer(actionType: Observable<HomeSendFragmentActionType>) : DisposableMapper() {
  private val mAccountInfo: PublishSubject<AccountMetaDataPair> = PublishSubject.create()

  val accountInfo: Observable<AccountMetaDataPair>
    get() = mAccountInfo

  init {
    actionType.ofType(HomeSendFragmentActionType.AccountInfo::class.java)
        .subscribe({
          mAccountInfo.onNext(it.accountMetaDataPair)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

  }
}