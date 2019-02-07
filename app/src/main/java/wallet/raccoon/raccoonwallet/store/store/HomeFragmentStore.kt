package wallet.raccoon.raccoonwallet.store.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.Store
import wallet.raccoon.raccoonwallet.store.creator.HomeFragmentActionCreator
import wallet.raccoon.raccoonwallet.store.getter.HomeFragmentGetter
import wallet.raccoon.raccoonwallet.store.reducer.HomeFragmentReducer
import wallet.raccoon.raccoonwallet.store.type.HomeFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeFragmentUseCase
import javax.inject.Inject

class HomeFragmentStore @Inject constructor(private val useCase: HomeFragmentUseCase) : Store<
        HomeFragmentActionType, HomeFragmentActionCreator, HomeFragmentReducer, HomeFragmentGetter>() {
    override fun createActionCreator(
        dispatch: (HomeFragmentActionType) -> Unit,
        reducer: HomeFragmentReducer
    ): HomeFragmentActionCreator {
        return HomeFragmentActionCreator(useCase, dispatch)
    }

    override fun createReducer(action: Observable<HomeFragmentActionType>): HomeFragmentReducer {
        return HomeFragmentReducer(action)
    }

    override fun createGetter(reducer: HomeFragmentReducer): HomeFragmentGetter {
        return HomeFragmentGetter(reducer)
    }

}