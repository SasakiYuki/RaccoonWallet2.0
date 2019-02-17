package wallet.raccoon.raccoonwallet.store.creator

import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.store.type.SelectWalletActivityActionType
import wallet.raccoon.raccoonwallet.usecase.SelectWalletActivityUseCase

class SelectWalletActivityActionCreator(
    private val useCase: SelectWalletActivityUseCase,
    private val dispatch: (SelectWalletActivityActionType) -> Unit
) : DisposableMapper() {

    suspend fun loadWallets() {
    }
}