package wallet.raccoon.raccoonwallet.view.controller

import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.epoxy.mosaic.OwnedMosaicHeaderModel_
import wallet.raccoon.raccoonwallet.model.epoxy.mosaic.OwnedMosaicRowModel_
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.util.ToastUtil

class OwnedMosaicSelectListController() : TypedEpoxyController<List<FullMosaicItem>>() {
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

          })
          .addTo(this)
    }
  }
}