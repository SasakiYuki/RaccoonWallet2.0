package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfoEntity
import wallet.raccoon.raccoonwallet.model.rest.TransactionEntity
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity
import wallet.raccoon.raccoonwallet.flux.store.HomeFragmentStore
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: HomeFragmentStore
) : BaseViewModel() {
  val harvestInfoData: MutableLiveData<HarvestInfoEntity> = MutableLiveData()
  val accountInfoData: MutableLiveData<AccountMetaDataPair> = MutableLiveData()
  val transactionList: MutableLiveData<TransactionEntity> = MutableLiveData()
  val nemPriceData: MutableLiveData<ZaifNemEntity> = MutableLiveData()
  val walletData: MutableLiveData<Wallet> = MutableLiveData()

  init {
    addDisposable(store.getter.harvestList
        .subscribe {
          harvestInfoData.postValue(it)
        })

    addDisposable(store.getter.accountInfo
        .subscribe {
          accountInfoData.postValue(it)
        })

    addDisposable(store.getter.transactionList
        .subscribe {
          transactionList.postValue(it)
        })

    addDisposable(store.getter.nemPrice
        .subscribe {
          nemPriceData.postValue(it)
        })

    addDisposable(store.getter.wallet
        .subscribe {
            walletData.postValue(it)
        })
  }

  suspend fun loadHarvestInfo(address: String) {
    store.actionCreator.loadHarvestInfo(context, address)
  }

  suspend fun loadAccountInfo(address: String) {
    store.actionCreator.loadAccountInfo(context, address)
  }

  suspend fun loadTransactionList(address: String) {
    store.actionCreator.loadTransactionList(context, address)
  }

  suspend fun loadNemPrice() {
    store.actionCreator.loadZaifNemPrice(context)
  }

  suspend fun loadWallet(walletId: Long){
      store.actionCreator.loadWallet(walletId)
  }
}