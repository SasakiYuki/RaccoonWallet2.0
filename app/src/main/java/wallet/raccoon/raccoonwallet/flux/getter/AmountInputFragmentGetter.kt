package wallet.raccoon.raccoonwallet.flux.getter

import wallet.raccoon.raccoonwallet.flux.reducer.AmountInputFragmentReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper

class AmountInputFragmentGetter(reducer: AmountInputFragmentReducer) : DisposableMapper() {
  val ownedMosaics = reducer.ownedMosaics
  val namespace = reducer.namespace
}