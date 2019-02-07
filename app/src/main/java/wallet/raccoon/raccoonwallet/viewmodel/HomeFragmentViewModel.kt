package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.HarvestInfo
import wallet.raccoon.raccoonwallet.store.store.HomeFragmentStore
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: HomeFragmentStore
) : BaseViewModel() {
  val harvestInfoData: MutableLiveData<List<HarvestInfo>> = MutableLiveData()

  suspend fun loadHarvestInfo(address: String) {
    store.actionCreator.loadHarvestInfo(context, address)
  }
}