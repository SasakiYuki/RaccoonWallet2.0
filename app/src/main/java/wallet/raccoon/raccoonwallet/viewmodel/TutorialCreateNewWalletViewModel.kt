package wallet.raccoon.raccoonwallet.viewmodel

import androidx.lifecycle.MutableLiveData
import wallet.raccoon.raccoonwallet.flux.store.TutorialCreateNewWalletStore
import wallet.raccoon.raccoonwallet.model.db.Wallet
import javax.inject.Inject

class TutorialCreateNewWalletViewModel @Inject constructor(private val store: TutorialCreateNewWalletStore) :
    BaseViewModel() {

    val createAndInsertWallet: MutableLiveData<Wallet> = MutableLiveData()

    init {
        addDisposable(store.getter.createAndInsertWallet
            .subscribe {
                createAndInsertWallet.postValue(it)
            })
    }

    suspend fun createAndSaveWallet(walletName: String) {
        store.actionCreator.createAndInsertWallet(walletName)
    }
}
