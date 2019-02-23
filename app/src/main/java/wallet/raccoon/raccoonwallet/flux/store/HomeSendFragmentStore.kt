package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.HomeSendFragmentActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.HomeSendFragmentGetter
import wallet.raccoon.raccoonwallet.flux.reducer.HomeSendFragmentReducer
import wallet.raccoon.raccoonwallet.flux.type.HomeSendFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeSendFragmentUseCase
import javax.inject.Inject

class HomeSendFragmentStore @Inject constructor(private val useCase: HomeSendFragmentUseCase) :
    Store<
        HomeSendFragmentActionType,
        HomeSendFragmentActionCreator,
        HomeSendFragmentReducer,
        HomeSendFragmentGetter
        >() {
  override fun createActionCreator(
    dispatch: (HomeSendFragmentActionType) -> Unit,
    reducer: HomeSendFragmentReducer
  ): HomeSendFragmentActionCreator = HomeSendFragmentActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<HomeSendFragmentActionType>) =
    HomeSendFragmentReducer(action)

  override fun createGetter(reducer: HomeSendFragmentReducer) = HomeSendFragmentGetter(reducer)
}