package wallet.raccoon.raccoonwallet.store.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.Store
import wallet.raccoon.raccoonwallet.store.creator.HomeSendFragmentActionCreator
import wallet.raccoon.raccoonwallet.store.getter.HomeSendFragmentGetter
import wallet.raccoon.raccoonwallet.store.reducer.HomeSendFragmentReducer
import wallet.raccoon.raccoonwallet.store.type.HomeSendFragmentActionType
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