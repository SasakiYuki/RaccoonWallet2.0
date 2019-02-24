package wallet.raccoon.raccoonwallet.flux.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.AmountInputFragmentActionType
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.usecase.AmountInputFragmentUseCase

class AmountInputFragmentActionCreator(
  private val useCase: AmountInputFragmentUseCase,
  private val dispatch: (AmountInputFragmentActionType) -> Unit
) : DisposableMapper() {
  suspend fun loadOwnedMosaics(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getOwnedMosaics(address),
        {
          dispatch(AmountInputFragmentActionType.OwnedMosaics(it))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadNamespaceMosaic(
    context: Context,
    namespace: String
  ) {
    Network.request(
        context,
        useCase.getNamespaceMosaic(namespace),
        {
          dispatch(AmountInputFragmentActionType.NamespaceMosaics(it))
        }, {
      it.printStackTrace()
    }
    )
  }
}