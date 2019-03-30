package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.MyProfileInfoActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.MyProfileInfoGetter
import wallet.raccoon.raccoonwallet.flux.reducer.MyProfileInfoReducer
import wallet.raccoon.raccoonwallet.flux.type.MyProfileInfoActionType
import wallet.raccoon.raccoonwallet.usecase.MyProfileInfoUseCase
import javax.inject.Inject

class MyProfileInfoStore @Inject constructor(private val useCase: MyProfileInfoUseCase) : Store<
    MyProfileInfoActionType, MyProfileInfoActionCreator, MyProfileInfoReducer, MyProfileInfoGetter>() {
  override fun createActionCreator(
    dispatch: (MyProfileInfoActionType) -> Unit,
    reducer: MyProfileInfoReducer
  ) = MyProfileInfoActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<MyProfileInfoActionType>) =
    MyProfileInfoReducer(action)

  override fun createGetter(reducer: MyProfileInfoReducer): MyProfileInfoGetter =
    MyProfileInfoGetter(reducer)
}
