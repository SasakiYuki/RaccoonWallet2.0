package wallet.raccoon.raccoonwallet.model.epoxy

import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.row_section_drawer_list)
abstract class DrawerSectionModel : DataBindingEpoxyModel()
