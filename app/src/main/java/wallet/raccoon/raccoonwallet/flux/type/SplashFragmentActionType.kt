package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity

sealed class SplashFragmentActionType {
    class ActiveNode(val activeNodeEntity: ActiveNodeEntity) : SplashFragmentActionType()
}