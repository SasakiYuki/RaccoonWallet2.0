package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.AmountInputFragmentActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.AmountInputFragmentGetter
import wallet.raccoon.raccoonwallet.flux.reducer.AmountInputFragmentReducer
import wallet.raccoon.raccoonwallet.flux.type.AmountInputFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.AmountInputFragmentUseCase
import javax.inject.Inject

class AmountInputFragmentStore @Inject constructor(private val useCase: AmountInputFragmentUseCase) :
    Store<AmountInputFragmentActionType,
        AmountInputFragmentActionCreator, AmountInputFragmentReducer, AmountInputFragmentGetter>() {
  override fun createActionCreator(
    dispatch: (AmountInputFragmentActionType) -> Unit,
    reducer: AmountInputFragmentReducer
  ) = AmountInputFragmentActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<AmountInputFragmentActionType>) =
    AmountInputFragmentReducer(action)

  override fun createGetter(reducer: AmountInputFragmentReducer) =
    AmountInputFragmentGetter(reducer)
}