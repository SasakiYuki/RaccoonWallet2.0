package wallet.raccoon.raccoonwallet.flux.getter

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.reducer.HomeSendFragmentReducer

class HomeSendFragmentGetter(reducer: HomeSendFragmentReducer) : DisposableMapper() {
  val accountInfo: Observable<AccountMetaDataPair> = reducer.accountInfo
}