package wallet.raccoon.raccoonwallet.view.controller

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.model.epoxy.SelectWalletEmptyModel_
import wallet.raccoon.raccoonwallet.model.epoxy.SelectWalletHeaderModel_
import wallet.raccoon.raccoonwallet.model.epoxy.SelectWalletRowModel_
import wallet.raccoon.raccoonwallet.model.local.SelectWalletItem
import wallet.raccoon.raccoonwallet.view.activity.SelectWalletActivity
import wallet.raccoon.raccoonwallet.viewmodel.SelectWalletViewModel

class SelectWalletListController(activity: SelectWalletActivity) : TypedEpoxyController<List<SelectWalletItem>>() {

    @AutoModel
    lateinit var selectWalletHeaderModel: SelectWalletHeaderModel_

    @AutoModel
    lateinit var selectWalletEmptyModel: SelectWalletEmptyModel_

    private val selectWalletViewModel: SelectWalletViewModel = ViewModelProviders.of(activity)
        .get(SelectWalletViewModel::class.java)

    override fun buildModels(data: List<SelectWalletItem>?) {
        data?.let {
            addList(data)
        }
    }

    private fun addList(data: List<SelectWalletItem>) {
        selectWalletHeaderModel
            .onClickCreateWallet(View.OnClickListener {
                selectWalletViewModel.navigationCreateWalletClickEvent.postValue(Unit)
            })
            .addTo(this)

        selectWalletEmptyModel.addIf(data.isEmpty(), this)

        for (item in data) {
            SelectWalletRowModel_()
                .id(modelCountBuiltSoFar)
                .selectWalletItem(item)
                .onClickRowListener(View.OnClickListener {
                    selectWalletViewModel.onClickRowEvent.postValue(item.wallet)
                })
                .onClickSettingListener(View.OnClickListener {
                    selectWalletViewModel.navigationSettingClickEvent.postValue(item.wallet)
                })
                .addTo(this)
        }
    }
}