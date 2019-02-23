package wallet.raccoon.raccoonwallet.flux.type

import wallet.raccoon.raccoonwallet.model.MyProfileEntity

sealed class MainActivityActionType {
    class MyProfile(val myProfileEntity: MyProfileEntity): MainActivityActionType()
}