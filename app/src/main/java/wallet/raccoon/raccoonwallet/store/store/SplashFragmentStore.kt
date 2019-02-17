package wallet.raccoon.raccoonwallet.store.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.Store
import wallet.raccoon.raccoonwallet.store.creator.SplashFragmentActionCreator
import wallet.raccoon.raccoonwallet.store.getter.SplashFragmentGetter
import wallet.raccoon.raccoonwallet.store.reducer.SplashFragmentReducer
import wallet.raccoon.raccoonwallet.store.type.SplashFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.SplashFragmentUseCase
import javax.inject.Inject

class SplashFragmentStore @Inject constructor(private val useCase: SplashFragmentUseCase) : Store<
        SplashFragmentActionType, SplashFragmentActionCreator, SplashFragmentReducer, SplashFragmentGetter>() {

    override fun createActionCreator(
        dispatch: (SplashFragmentActionType) -> Unit,
        reducer: SplashFragmentReducer
    ): SplashFragmentActionCreator {
        return SplashFragmentActionCreator(useCase, dispatch)
    }

    override fun createReducer(action: Observable<SplashFragmentActionType>): SplashFragmentReducer {
        return SplashFragmentReducer(action)
    }

    override fun createGetter(reducer: SplashFragmentReducer): SplashFragmentGetter {
        return SplashFragmentGetter(reducer)
    }
}