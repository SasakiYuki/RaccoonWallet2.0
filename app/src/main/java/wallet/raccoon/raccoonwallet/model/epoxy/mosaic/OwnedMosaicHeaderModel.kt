package wallet.raccoon.raccoonwallet.model.epoxy.mosaic

import android.widget.CompoundButton
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.list_owned_mosaic_header)
abstract class OwnedMosaicHeaderModel : DataBindingEpoxyModel() {
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var switchChangeListener: CompoundButton.OnCheckedChangeListener =
    CompoundButton.OnCheckedChangeListener { compoundButton, b -> }

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var checked: Boolean = false

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding?.let {
      it.setVariable(BR.switchChangeListener, switchChangeListener)
      it.setVariable(BR.checked, checked)
    }
  }
}