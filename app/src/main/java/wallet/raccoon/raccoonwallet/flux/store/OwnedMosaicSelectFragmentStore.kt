package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.OwnedMosaicSelectFragmentActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.OwnedMosaicSelectFragmentGetter
import wallet.raccoon.raccoonwallet.flux.reducer.OwnedMosaicSelectFragmentReducer
import wallet.raccoon.raccoonwallet.flux.type.OwnedMosaicSelectFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.OwnedMosaicSelectFragmentUseCase
import javax.inject.Inject

class OwnedMosaicSelectFragmentStore @Inject constructor(private val useCase: OwnedMosaicSelectFragmentUseCase) :
    Store<OwnedMosaicSelectFragmentActionType,
        OwnedMosaicSelectFragmentActionCreator, OwnedMosaicSelectFragmentReducer, OwnedMosaicSelectFragmentGetter>() {
  override fun createActionCreator(
    dispatch: (OwnedMosaicSelectFragmentActionType) -> Unit,
    reducer: OwnedMosaicSelectFragmentReducer
  ) = OwnedMosaicSelectFragmentActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<OwnedMosaicSelectFragmentActionType>) =
    OwnedMosaicSelectFragmentReducer(action)

  override fun createGetter(reducer: OwnedMosaicSelectFragmentReducer) =
    OwnedMosaicSelectFragmentGetter(reducer)
}