package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.HomeFragmentActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.HomeFragmentGetter
import wallet.raccoon.raccoonwallet.flux.reducer.HomeFragmentReducer
import wallet.raccoon.raccoonwallet.flux.type.HomeFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeFragmentUseCase
import javax.inject.Inject

class HomeFragmentStore @Inject constructor(private val useCase: HomeFragmentUseCase) : Store<
    HomeFragmentActionType, HomeFragmentActionCreator, HomeFragmentReducer, HomeFragmentGetter>() {
  override fun createActionCreator(
    dispatch: (HomeFragmentActionType) -> Unit,
    reducer: HomeFragmentReducer
  ): HomeFragmentActionCreator {
    return HomeFragmentActionCreator(useCase, dispatch)
  }

  override fun createReducer(action: Observable<HomeFragmentActionType>): HomeFragmentReducer {
    return HomeFragmentReducer(action)
  }

  override fun createGetter(reducer: HomeFragmentReducer): HomeFragmentGetter {
    return HomeFragmentGetter(reducer)
  }

}

