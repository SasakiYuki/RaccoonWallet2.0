package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_select_wallet.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.local.SelectWalletItem
import wallet.raccoon.raccoonwallet.view.BaseActivity
import wallet.raccoon.raccoonwallet.view.controller.SelectWalletListController
import wallet.raccoon.raccoonwallet.viewmodel.SelectWalletViewModel
import javax.inject.Inject

class SelectWalletActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SelectWalletViewModel
    private lateinit var controller: SelectWalletListController

    override fun setLayout() = R.layout.activity_select_wallet

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupViews()
        loadWallets()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SelectWalletViewModel::class.java)

//        viewModel.allWalletsData.observe(this, Observer { wallets ->
//            viewModel.selectedWalletIdData.observe(this, Observer { selectedWalletId ->
//                controller.setData(
//                    wallets.map { wallet ->
//                        SelectWalletItem(wallet = wallet, isSelected = wallet.id == selectedWalletId)
//                    })
//            })
//        })

        viewModel.selectedWalletIdData.observe(this, Observer { selectedWalletId ->
            viewModel.allWalletsData.observe(this, Observer { wallets ->
                controller.setData(
                    wallets.map { wallet ->
                        SelectWalletItem(wallet = wallet, isSelected = wallet.id == selectedWalletId)
                    }
                )
            })
        })

        viewModel.saveSelectedWalletId.observe(this, Observer {
            //            loadWallets()
        })

        viewModel.navigationCreateWalletClickEvent.observe(this, Observer {
            //todo ウォレット作成へ遷移させる
        })

        viewModel.navigationSettingClickEvent.observe(this, Observer {
            //todo ウォレット設定へ遷移させる
        })

        viewModel.onClickRowEvent.observe(this, Observer { wallet ->
            val list = controller.currentData?.let { wallets ->
                wallets.map {
                    SelectWalletItem(it.wallet, it.wallet.id == wallet.id)
                }
            } ?: run { ArrayList<SelectWalletItem>() }

            controller.setData(list)
            viewModel.saveSelectedWalletId(wallet.id)
        })

    }

    private fun loadWallets() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadSelectedWalletId()
            viewModel.loadAllWallets()
        }
    }

    private fun setupViews() {
        setToolbarTitle(R.string.select_wallet_activity_title)

        controller = SelectWalletListController(this)

        walletSelectRecyclerView.layoutManager = LinearLayoutManager(this)
        walletSelectRecyclerView.setController(controller)

        controller.setData(ArrayList())
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, SelectWalletActivity::class.java)
    }
}