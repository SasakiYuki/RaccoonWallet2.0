package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.creator.SplashFragmentActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.SplashFragmentGetter
import wallet.raccoon.raccoonwallet.flux.reducer.SplashFragmentReducer
import wallet.raccoon.raccoonwallet.flux.type.SplashFragmentActionType
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