package wallet.raccoon.raccoonwallet.view.controller

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.model.epoxy.mosaic.OwnedMosaicHeaderModel_
import wallet.raccoon.raccoonwallet.model.epoxy.mosaic.OwnedMosaicRowModel_
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.view.activity.SendActivity
import wallet.raccoon.raccoonwallet.viewmodel.send.SendActivityViewModel

class OwnedMosaicSelectListController(val activity: SendActivity) : TypedEpoxyController<List<FullMosaicItem>>() {
  @AutoModel
  lateinit var ownedMosaicHeaderModel: OwnedMosaicHeaderModel_

  override fun buildModels(data: List<FullMosaicItem>?) {
    data?.let {
      addList(it)
    }
  }

  private fun addList(data: List<FullMosaicItem>) {
    ownedMosaicHeaderModel
        .checked(true)
        .switchChangeListener { _, b ->
          ViewModelProviders.of(activity)
              .get(SendActivityViewModel::class.java)
              .switchStateData.postValue(b)
        }
        .addTo(this)
    val formattedList = ArrayList<FullMosaicItem>()
    data.filterNotTo(formattedList) {
      it.mosaicItem.isNEMXEMItem()
    }

    for (item in formattedList) {
      OwnedMosaicRowModel_()
          .id(modelCountBuiltSoFar)
          .mosaicFullItem(item)
          .itemClickListener(View.OnClickListener {
            ViewModelProviders.of(activity)
                .get(SendActivityViewModel::class.java)
                .mosaicSelectedData.postValue(item)
          })
          .addTo(this)
    }
  }
}