package wallet.raccoon.raccoonwallet.model.epoxy.send

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.model.local.SendMosaicItem

@EpoxyModelClass(layout = R.layout.row_send_confirm_main)
abstract class SendConfirmMainRowModel : DataBindingEpoxyModel() {
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var sendMosaicItem: SendMosaicItem? = null

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding?.let {
      it.setVariable(BR.sendMosaicItem, sendMosaicItem)
    }
  }
}
