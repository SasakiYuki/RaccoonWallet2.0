package wallet.raccoon.raccoonwallet.viewmodel.send

import android.content.Context
import androidx.lifecycle.MutableLiveData
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.viewmodel.BaseViewModel
import javax.inject.Inject

class SendActivityViewModel @Inject constructor(
  private val context: Context
) : BaseViewModel() {
  val mosaicSelectedData: MutableLiveData<FullMosaicItem> = MutableLiveData()
  val switchStateData: MutableLiveData<Boolean> = MutableLiveData()
  val automaticAddedXEMData: MutableLiveData<Unit> = MutableLiveData()
  val addedMosaicData: MutableLiveData<Unit> = MutableLiveData()
  val calculateFinishData: MutableLiveData<Unit> = MutableLiveData()
}
