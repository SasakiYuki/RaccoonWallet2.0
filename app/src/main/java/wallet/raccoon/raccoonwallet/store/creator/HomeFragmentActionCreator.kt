package wallet.raccoon.raccoonwallet.store.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.store.type.HomeFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeFragmentUseCase

class HomeFragmentActionCreator(
    private val useCase: HomeFragmentUseCase,
    private val dispatch: (HomeFragmentActionType) -> Unit
) : DisposableMapper() {
    suspend fun loadAccountInfo(context: Context) {

    }

    suspend fun loadTransactionList(context: Context) {

    }

    suspend fun loadHarvestInfo(
        context: Context,
        address: String
    ) {
        Network.request(
            context,
            useCase.getHarvestInfo(address),
            {
                dispatch(HomeFragmentActionType.HarvestInfo(it))
            }, {
                it.printStackTrace()
            }
        )
    }
}