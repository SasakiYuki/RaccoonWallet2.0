package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import wallet.raccoon.raccoonwallet.flux.store.HomeSendFragmentStore
import javax.inject.Inject

class HomeSendFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: HomeSendFragmentStore
) : BaseViewModel() {
  val accountInfoData: MutableLiveData<AccountMetaDataPair> = MutableLiveData()

  init {
    addDisposable(
        store.getter.accountInfo
            .subscribe {
              accountInfoData.postValue(it)
            }
    )
  }

  suspend fun loadAccountInfo(address: String) {
    store.actionCreator.loadAccountInfo(context, address)
  }
}