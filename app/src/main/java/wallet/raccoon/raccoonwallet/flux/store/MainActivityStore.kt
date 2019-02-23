package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.MainActivityActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.MainActivityGetter
import wallet.raccoon.raccoonwallet.flux.reducer.MainActivityReducer
import wallet.raccoon.raccoonwallet.flux.type.MainActivityActionType
import wallet.raccoon.raccoonwallet.usecase.MainActivityUseCase
import javax.inject.Inject

class MainActivityStore @Inject constructor(private val useCase: MainActivityUseCase) : Store<
    MainActivityActionType, MainActivityActionCreator, MainActivityReducer, MainActivityGetter>() {
  override fun createActionCreator(
    dispatch: (MainActivityActionType) -> Unit,
    reducer: MainActivityReducer
  ): MainActivityActionCreator {
    return MainActivityActionCreator(useCase, dispatch)
  }

  override fun createReducer(action: Observable<MainActivityActionType>): MainActivityReducer {
    return MainActivityReducer(action)
  }

  override fun createGetter(reducer: MainActivityReducer): MainActivityGetter {
    return MainActivityGetter(reducer)
  }
}