package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.MyWalletInfoActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.MyWalletInfoGetter
import wallet.raccoon.raccoonwallet.flux.reducer.MyWalletInfoReducer
import wallet.raccoon.raccoonwallet.flux.type.MyWalletInfoActionType
import wallet.raccoon.raccoonwallet.usecase.MyWalletInfoUseCase
import javax.inject.Inject

class MyWalletInfoStore @Inject constructor(private val useCase: MyWalletInfoUseCase) : Store<
    MyWalletInfoActionType, MyWalletInfoActionCreator, MyWalletInfoReducer, MyWalletInfoGetter>() {
  override fun createActionCreator(
    dispatch: (MyWalletInfoActionType) -> Unit,
    reducer: MyWalletInfoReducer
  ) = MyWalletInfoActionCreator(useCase, dispatch)

  override fun createReducer(action: Observable<MyWalletInfoActionType>) =
    MyWalletInfoReducer(action)

  override fun createGetter(reducer: MyWalletInfoReducer) = MyWalletInfoGetter(reducer)
}
