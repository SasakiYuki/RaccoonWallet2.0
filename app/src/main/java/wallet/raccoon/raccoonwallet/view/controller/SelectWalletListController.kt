package wallet.raccoon.raccoonwallet.view.controller

import android.util.Log
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
                Log.d("", "")
            })
            .addTo(this)

        selectWalletEmptyModel.addIf(data.isEmpty(), this)

        for (item in data) {
            SelectWalletRowModel_()
                .id(modelCountBuiltSoFar)
                .selectWalletItem(item)
                .onClickRowListener(View.OnClickListener {
                    Log.d("", "")
                })
                .onClickSettingListener(View.OnClickListener {
                    Log.d("", "")
                })
                .addTo(this)
        }
    }
}