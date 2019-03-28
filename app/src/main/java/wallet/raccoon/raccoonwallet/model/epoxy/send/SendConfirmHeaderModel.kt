package wallet.raccoon.raccoonwallet.model.epoxy.send

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.row_header_send_confirm)
abstract class SendConfirmHeaderModel : DataBindingEpoxyModel() {
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var title: String? = null

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding?.let {
      it.setVariable(BR.title, title)
    }
  }
}
