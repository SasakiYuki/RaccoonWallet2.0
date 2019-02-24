package wallet.raccoon.raccoonwallet.flux.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.flux.type.HomeSendFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeSendFragmentUseCase

class HomeSendFragmentActionCreator(
  private val useCase: HomeSendFragmentUseCase,
  private val dispatch: (HomeSendFragmentActionType) -> Unit
) : DisposableMapper() {
  suspend fun loadAccountInfo(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getAccountInfo(address),
        {
          dispatch(HomeSendFragmentActionType.AccountInfo(it))
        }, {
      it.printStackTrace()
    }
    )
  }
}