package wallet.raccoon.raccoonwallet.model.epoxy.wallet

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

@EpoxyModelClass(layout = R.layout.row_wallet_info)
abstract class WalletInfoListModel : DataBindingEpoxyModel() {
  @EpoxyAttribute
  var walletInfo: WalletInfo? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var onClickRowListener: View.OnClickListener = View.OnClickListener { }

  override fun setDataBindingVariables(binding: ViewDataBinding?) {
    binding?.apply {
      setVariable(BR.walletInfo, walletInfo)
      setVariable(BR.clickListener, onClickRowListener)
    }
  }
}
