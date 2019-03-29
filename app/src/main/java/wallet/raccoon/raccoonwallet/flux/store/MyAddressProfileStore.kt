package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.MyAddressProfileActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.MyAddressProfileGetter
import wallet.raccoon.raccoonwallet.flux.reducer.MyAddressProfileReducer
import wallet.raccoon.raccoonwallet.flux.type.MyAddressProfileActionType
import wallet.raccoon.raccoonwallet.usecase.MyAddressProfileUseCase
import javax.inject.Inject

class MyAddressProfileStore @Inject constructor(private val useCase: MyAddressProfileUseCase) :
    Store<
        MyAddressProfileActionType, MyAddressProfileActionCreator, MyAddressProfileReducer, MyAddressProfileGetter>() {
  override fun createActionCreator(
    dispatch: (MyAddressProfileActionType) -> Unit,
    reducer: MyAddressProfileReducer
  ) = MyAddressProfileActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<MyAddressProfileActionType>) =
    MyAddressProfileReducer(action)

  override fun createGetter(reducer: MyAddressProfileReducer) = MyAddressProfileGetter(reducer)
}
