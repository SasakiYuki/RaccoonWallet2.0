package wallet.raccoon.raccoonwallet.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.Store
import wallet.raccoon.raccoonwallet.store.creator.MainActivityActionCreator
import wallet.raccoon.raccoonwallet.store.reducer.MainActivityReducer
import wallet.raccoon.raccoonwallet.store.type.MainActivityActionType
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