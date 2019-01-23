package wallet.raccoon.raccoonwallet.store.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.store.type.MainActivityActionType
import wallet.raccoon.raccoonwallet.usecase.MainActivityUseCase

class MainActivityActionCreator(
  private val useCase: MainActivityUseCase,
  private val dispatch: (MainActivityActionType) -> Unit
) : DisposableMapper() {

  suspend fun loadMyProfile(context: Context) {
    Network.request(
        context,
        useCase.loadMyProfile(),
        {
          dispatch(MainActivityActionType.MyProfile(it))
        }, {}
    )
  }
}