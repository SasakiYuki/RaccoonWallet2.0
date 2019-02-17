package wallet.raccoon.raccoonwallet.store.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.store.type.SplashFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.SplashFragmentUseCase

class SplashFragmentActionCreator(
    private val useCase: SplashFragmentUseCase,
    private val dispatch: (SplashFragmentActionType) -> Unit
) : DisposableMapper() {

    suspend fun loadActiveNodeList(context: Context) {
        Network.request(
            context,
            useCase.getActiveNodeList(),
            {
                dispatch(SplashFragmentActionType.ActiveNode(it))
            }, {
                it.printStackTrace()
            }
        )
    }
}