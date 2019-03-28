package wallet.raccoon.raccoonwallet.model.epoxy

import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.row_empty_select_wallet)
abstract class SelectWalletEmptyModel : DataBindingEpoxyModel()