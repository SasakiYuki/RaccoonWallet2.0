package wallet.raccoon.raccoonwallet.store.getter

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.store.reducer.HomeSendFragmentReducer

class HomeSendFragmentGetter(reducer: HomeSendFragmentReducer) : DisposableMapper() {
  val accountInfo: Observable<AccountMetaDataPair> = reducer.accountInfo
}