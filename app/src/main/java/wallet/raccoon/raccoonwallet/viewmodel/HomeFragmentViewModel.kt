package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.HarvestInfo
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos
import wallet.raccoon.raccoonwallet.store.store.HomeFragmentStore
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: HomeFragmentStore
) : BaseViewModel() {
  val harvestInfoData: MutableLiveData<HarvestInfos> = MutableLiveData()

  init {
    addDisposable(store.getter.harvestList
        .subscribe{
          harvestInfoData.postValue(it)
        })
  }

  suspend fun loadHarvestInfo(address: String) {
    store.actionCreator.loadHarvestInfo(context, address)
  }
}