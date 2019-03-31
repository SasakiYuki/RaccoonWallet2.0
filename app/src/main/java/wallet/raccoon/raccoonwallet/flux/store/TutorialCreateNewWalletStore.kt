package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.TutorialCreateNewWalletActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.TutorialCreateNewWalletGetter
import wallet.raccoon.raccoonwallet.flux.reducer.TutorialCreateNewWalletReducer
import wallet.raccoon.raccoonwallet.flux.type.TutorialCreateNewWalletActionType
import wallet.raccoon.raccoonwallet.usecase.TutorialCreateNewWalletUseCase
import javax.inject.Inject

class TutorialCreateNewWalletStore @Inject constructor(private val useCase: TutorialCreateNewWalletUseCase) : Store<
        TutorialCreateNewWalletActionType, TutorialCreateNewWalletActionCreator, TutorialCreateNewWalletReducer, TutorialCreateNewWalletGetter>() {

    override fun createActionCreator(
        dispatch: (TutorialCreateNewWalletActionType) -> Unit,
        reducer: TutorialCreateNewWalletReducer
    ): TutorialCreateNewWalletActionCreator {
        return TutorialCreateNewWalletActionCreator(useCase, dispatch)
    }

    override fun createReducer(action: Observable<TutorialCreateNewWalletActionType>): TutorialCreateNewWalletReducer {
        return TutorialCreateNewWalletReducer(action)
    }

    override fun createGetter(reducer: TutorialCreateNewWalletReducer): TutorialCreateNewWalletGetter {
        return TutorialCreateNewWalletGetter(reducer)
    }
}