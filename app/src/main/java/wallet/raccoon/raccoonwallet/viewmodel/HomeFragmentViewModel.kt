package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.store.store.HomeFragmentStore
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: HomeFragmentStore
) : BaseViewModel() {
  val harvestInfoData: MutableLiveData<HarvestInfos> = MutableLiveData()
  val accountInfoData: MutableLiveData<AccountMetaDataPair> = MutableLiveData()
  val transactionList: MutableLiveData<List<TransactionMetaDataPair>> = MutableLiveData()

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
}