package wallet.raccoon.raccoonwallet.viewmodel

import androidx.lifecycle.MutableLiveData
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.store.SelectWalletStore
import javax.inject.Inject

class SelectWalletViewModel @Inject constructor(private val store: SelectWalletStore) : BaseViewModel() {
    val allWalletsData: MutableLiveData<List<Wallet>> = MutableLiveData()
    val onClickRowEvent: MutableLiveData<Wallet> = MutableLiveData()
    val navigationCreateWalletClickEvent: MutableLiveData<Unit> = MutableLiveData()
    val navigationSettingClickEvent: MutableLiveData<Wallet> = MutableLiveData()

    init {
        addDisposable(store.getter.wallets
            .subscribe {
                allWalletsData.postValue(it)
            })
    }

    suspend fun loadAllWallets() {
        store.actionCreator.loadAllWallets()
    }
}