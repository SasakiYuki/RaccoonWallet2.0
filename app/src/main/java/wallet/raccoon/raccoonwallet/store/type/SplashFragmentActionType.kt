package wallet.raccoon.raccoonwallet.store.type

import wallet.raccoon.raccoonwallet.model.ActiveNodeEntity

sealed class SplashFragmentActionType {
    class ActiveNode(val activeNodeEntity: ActiveNodeEntity) : SplashFragmentActionType()
}