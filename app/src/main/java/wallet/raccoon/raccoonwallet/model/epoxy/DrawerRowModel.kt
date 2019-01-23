package wallet.raccoon.raccoonwallet.model.epoxy

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
//import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.DrawerEntity

@EpoxyModelClass(layout = R.layout.row_drawer)
abstract class DrawerRowModel : DataBindingEpoxyModel() {
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var onClickRowListener: View.OnClickListener = View.OnClickListener { }

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var drawerEntity: DrawerEntity? = null

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
//    binding?.setVariable(BR.clickListener, onClickRowListener)
//    binding?.setVariable(BR.drawer, drawerEntity)
  }
}
