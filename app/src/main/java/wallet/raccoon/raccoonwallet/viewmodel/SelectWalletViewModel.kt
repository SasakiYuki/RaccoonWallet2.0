package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.store.store.SelectWalletStore
import javax.inject.Inject

class SelectWalletViewModel @Inject constructor(
    private val context: Context,
    private val store: SelectWalletStore
) : BaseViewModel() {
    val allWalletsData: MutableLiveData<List<Wallet>> = MutableLiveData()
    val selectedWalletIdData: MutableLiveData<Long> = MutableLiveData()
    val accountInfoData: MutableLiveData<AccountMetaDataPair> = MutableLiveData()
    val updateWallet: MutableLiveData<Long> = MutableLiveData()

    val onClickRowEvent: MutableLiveData<Wallet> = MutableLiveData()
    val navigationCreateWalletClickEvent: MutableLiveData<Unit> = MutableLiveData()
    val navigationSettingClickEvent: MutableLiveData<Wallet> = MutableLiveData()

    init {
        addDisposable(store.getter.wallets
            .subscribe {
                allWalletsData.postValue(it)
            })

        addDisposable(store.getter.selectedWalletId
            .subscribe {
                selectedWalletIdData.postValue(it)
            })

        addDisposable(store.getter.saveSelectedWalletId
            .subscribe {})

        addDisposable(store.getter.accountInfo
            .subscribe {
                accountInfoData.postValue(it)
            })

        addDisposable(store.getter.updateWallet
            .subscribe {
                updateWallet.postValue(it)
            })
    }

    suspend fun loadAllWallets() {
        store.actionCreator.loadAllWallets()
    }

    fun loadSelectedWalletId() {
        store.actionCreator.loadSelectedWalletId()
    }

    fun saveSelectedWalletId(selectedWalletId: Long) {
        store.actionCreator.saveSelectedWalletId(selectedWalletId)
    }

    suspend fun loadAccountInfo(address: String) {
        store.actionCreator.loadAccountInfo(context, address)
    }

    suspend fun updateWallet(wallet: Wallet) {
        store.actionCreator.updateWallet(wallet)
    }
}