package wallet.raccoon.raccoonwallet.view.controller

import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

class WalletInfoListController(val walletInfoClickListener: WalletInfoClickListener) : TypedEpoxyController<List<WalletInfo>?>() {
  @AutoModel
  lateinit var walletInfoEmptyModel: WalletInfoEmptyModel_

  override fun buildModels(data: List<WalletInfo>?) {
    data?.let {
      walletInfoEmptyModel.addIf(it.isEmpty(), this)
      for (item in it) {
        WalletInfoListModel_()
            .id(modelCountBuiltSoFar)
            .walletInfo(item)
            .onClickRowListener(View.OnClickListener {
              walletInfoClickListener.onRowClick(item)
            })
            .addTo(this)
      }
    } ?: run {
      walletInfoEmptyModel.addTo(this)
    }
  }
}

interface WalletInfoClickListener {
  fun onRowClick(walletInfo: WalletInfo)
}
