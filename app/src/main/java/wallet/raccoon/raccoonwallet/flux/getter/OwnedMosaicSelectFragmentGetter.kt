package wallet.raccoon.raccoonwallet.flux.getter

import wallet.raccoon.raccoonwallet.flux.reducer.OwnedMosaicSelectFragmentReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper

class OwnedMosaicSelectFragmentGetter(reducer: OwnedMosaicSelectFragmentReducer) : DisposableMapper() {
  val ownedMosaics = reducer.ownedMosaics
  val namespace = reducer.namespace
}