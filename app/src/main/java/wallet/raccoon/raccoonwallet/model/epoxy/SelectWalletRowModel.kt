package wallet.raccoon.raccoonwallet.model.epoxy

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.BR
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.SelectWalletItem

@EpoxyModelClass(layout = R.layout.row_select_wallet)
abstract class SelectWalletRowModel : DataBindingEpoxyModel() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickRowListener: View.OnClickListener = View.OnClickListener { }

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickSettingListener: View.OnClickListener = View.OnClickListener { }

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var selectWalletItem: SelectWalletItem? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.rowClickListener, onClickRowListener)
        binding?.setVariable(BR.settingClickListener, onClickSettingListener)
        binding?.setVariable(BR.item, selectWalletItem)
    }
}