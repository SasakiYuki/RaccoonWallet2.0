package wallet.raccoon.raccoonwallet.model.epoxy

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.row_header_selet_wallet)
abstract class SelectWalletHeaderModel : DataBindingEpoxyModel() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickCreateWallet: View.OnClickListener = View.OnClickListener { }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.createWalletClickListener, onClickCreateWallet)
    }
}