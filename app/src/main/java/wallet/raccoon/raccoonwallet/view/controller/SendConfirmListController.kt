package wallet.raccoon.raccoonwallet.view.controller

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.extentions.toDisplayAddress
import wallet.raccoon.raccoonwallet.model.epoxy.send.SendConfirmHeaderModel_
import wallet.raccoon.raccoonwallet.model.epoxy.send.SendConfirmMainRowModel_
import wallet.raccoon.raccoonwallet.model.epoxy.send.SendConfirmSubRowModel_
import wallet.raccoon.raccoonwallet.model.epoxy.send.SimpleSendHeaderModel_
import wallet.raccoon.raccoonwallet.model.local.SendMosaicItem

class SendConfirmListController(
  val context: Context,
  private val fee: Double,
  private val address: String,
  private val message: String = ""
) : TypedEpoxyController<ArrayList<SendMosaicItem>>() {

  override fun buildModels(data: ArrayList<SendMosaicItem>?) {
    data?.let {
      addList(data)
    }
  }

  private fun addList(data: ArrayList<SendMosaicItem>) {
    SimpleSendHeaderModel_()
        .id(modelCountBuiltSoFar)
        .addTo(this)

    SendConfirmHeaderModel_()
        .id(modelCountBuiltSoFar)
        .title(context.getString(R.string.send_confirm_fragment_amount))
        .addTo(this)

    for (item in data) {
      SendConfirmMainRowModel_()
          .id(modelCountBuiltSoFar)
          .sendMosaicItem(item)
          .addTo(this)
    }

    SendConfirmHeaderModel_()
        .id(modelCountBuiltSoFar)
        .title(context.getString(R.string.send_confirm_fragment_fee))
        .addTo(this)

    SendConfirmSubRowModel_()
        .id(modelCountBuiltSoFar)
        .title(fee.toString())
        .addTo(this)

    SendConfirmHeaderModel_()
        .id(modelCountBuiltSoFar)
        .title(context.getString(R.string.send_confirm_fragment_send_address))
        .addTo(this)

    SendConfirmSubRowModel_()
        .id(modelCountBuiltSoFar)
        .title(address.toDisplayAddress())
        .addTo(this)

    SendConfirmHeaderModel_()
        .id(modelCountBuiltSoFar)
        .title(context.getString(R.string.send_confirm_fragment_send_title))
        .addIf(message != "", this)

    SendConfirmSubRowModel_()
        .id(modelCountBuiltSoFar)
        .title(message)
        .addIf(message != "", this)
  }
}
