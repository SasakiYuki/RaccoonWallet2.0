package wallet.raccoon.raccoonwallet.viewmodel

import wallet.raccoon.raccoonwallet.flux.store.TutorialCreateNewWalletStore
import javax.inject.Inject

class TutorialCreateNewWalletViewModel @Inject constructor(private val store: TutorialCreateNewWalletStore) :
    BaseViewModel() {
}