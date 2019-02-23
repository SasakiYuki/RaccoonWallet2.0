package wallet.raccoon.raccoonwallet.store.creator

import wallet.raccoon.raccoonwallet.db.Database
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.store.type.SelectWalletActivityActionType
import wallet.raccoon.raccoonwallet.usecase.SelectWalletActivityUseCase

class SelectWalletActivityActionCreator(
    private val useCase: SelectWalletActivityUseCase,
    private val dispatch: (SelectWalletActivityActionType) -> Unit
) : DisposableMapper() {

    suspend fun loadAllWallets() {
        Database.query(useCase.getAllWallet(), {
            dispatch(SelectWalletActivityActionType.Wallets(it))
        })
    }

    fun loadSelectedWalletId() {
        Database.query(useCase.getSelectedWalletId(), {
            dispatch(SelectWalletActivityActionType.SelectedWalletId(it))
        })
    }

    fun saveSelectedWalletId(selectedWalletId: Long) {
        dispatch(SelectWalletActivityActionType.SaveSelectedWalletId(useCase.saveSelectedWalletId(selectedWalletId)))
    }
}