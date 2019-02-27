package wallet.raccoon.raccoonwallet.model.epoxy.mosaic

import android.widget.CompoundButton
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.switchChangeListener
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.list_owned_mosaic_header)
abstract class OwnedMosaicHeaderModel : DataBindingEpoxyModel() {
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var swichChangelistener: CompoundButton.OnCheckedChangeListener =
    CompoundButton.OnCheckedChangeListener { button, b ->
    }

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var checked: Boolean = false

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding!!.setVariable(BR.switchChangeListener, switchChangeListener)
    binding.setVariable(BR.checked, checked)
  }
}