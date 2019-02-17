package wallet.raccoon.raccoonwallet.store.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.Store
import wallet.raccoon.raccoonwallet.store.creator.SelectWalletActivityActionCreator
import wallet.raccoon.raccoonwallet.store.getter.SelectWalletActivityGetter
import wallet.raccoon.raccoonwallet.store.reducer.SelectWalletActivityReducer
import wallet.raccoon.raccoonwallet.store.type.SelectWalletActivityActionType
import wallet.raccoon.raccoonwallet.usecase.SelectWalletActivityUseCase
import javax.inject.Inject

class SelectWalletStore @Inject constructor(private val useCase: SelectWalletActivityUseCase) : Store<
        SelectWalletActivityActionType, SelectWalletActivityActionCreator, SelectWalletActivityReducer, SelectWalletActivityGetter>() {
    override fun createActionCreator(
        dispatch: (SelectWalletActivityActionType) -> Unit,
        reducer: SelectWalletActivityReducer
    ): SelectWalletActivityActionCreator {
        return SelectWalletActivityActionCreator(useCase, dispatch)
    }

    override fun createReducer(action: Observable<SelectWalletActivityActionType>): SelectWalletActivityReducer {
        return SelectWalletActivityReducer(action)
    }

    override fun createGetter(reducer: SelectWalletActivityReducer): SelectWalletActivityGetter {
        return SelectWalletActivityGetter(reducer)
    }
}