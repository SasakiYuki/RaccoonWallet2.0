package wallet.raccoon.raccoonwallet.model.epoxy.mosaic

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.BR

@EpoxyModelClass(layout = R.layout.list_owned_mosaic)
abstract class OwnedMosaicRowModel : DataBindingEpoxyModel() {
  @EpoxyAttribute
  var mosaicFullItem: FullMosaicItem? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var itemClickListener: View.OnClickListener = View.OnClickListener { }

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding?.let {
      it.setVariable(BR.mosaicFullItem, mosaicFullItem)
      it.setVariable(BR.itemClickListener, itemClickListener)
    }
  }
}