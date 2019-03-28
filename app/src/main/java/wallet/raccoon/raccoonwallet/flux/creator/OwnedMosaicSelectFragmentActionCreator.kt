package wallet.raccoon.raccoonwallet.flux.creator

import android.content.Context
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.OwnedMosaicSelectFragmentActionType
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.usecase.OwnedMosaicSelectFragmentUseCase

class OwnedMosaicSelectFragmentActionCreator(
  private val useCase: OwnedMosaicSelectFragmentUseCase,
  private val dispatch: (OwnedMosaicSelectFragmentActionType) -> Unit
) : DisposableMapper() {
  suspend fun loadOwnedMosaics(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getOwnedMosaics(address),
        {
          dispatch(OwnedMosaicSelectFragmentActionType.OwnedMosaics(it.data))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadNamespaceMosaic(
    context: Context,
    namespace: String
  ): Deferred<Boolean> {
    val deferred = CompletableDeferred<Boolean>()
    Network.request(
        context,
        useCase.getNamespaceMosaic(namespace),
        {
          deferred.complete(true)
          dispatch(
              OwnedMosaicSelectFragmentActionType.NamespaceMosaics(
                  Pair(
                      namespace,
                      it.data
                  )
              )
          )
        }, {
      it.printStackTrace()
      deferred.complete(false)
    }
    )
    return deferred
  }
}