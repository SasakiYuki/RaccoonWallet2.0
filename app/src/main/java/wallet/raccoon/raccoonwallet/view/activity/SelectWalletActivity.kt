package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SelectWalletViewModel::class.java)

        viewModel.allWalletsData.observe(this, Observer { wallets ->
            controller.setData(
                wallets.map {
                    //todo isSelectをPreferenceから取得する
                    SelectWalletItem(wallet = it, isSelected = true)
                })
        })

        viewModel.navigationCreateWalletClickEvent.observe(this, Observer {
            //todo ウォレット作成へ遷移させる
        })

        viewModel.navigationSettingClickEvent.observe(this, Observer {
            //todo ウォレット設定へ遷移させる
        })

        viewModel.onClickRowEvent.observe(this, Observer {
            //todo Walletを選択した際の処理を実行する
        })

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadAllWallets()
        }
    }

    private fun setupViews() {
        setToolbarTitle(R.string.select_wallet_activity_title)

        controller = SelectWalletListController(this)

        walletSelectRecyclerView.layoutManager = LinearLayoutManager(this)
        walletSelectRecyclerView.adapter = controller.adapter

        val list = ArrayList<SelectWalletItem>()
        controller.setData(list)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, SelectWalletActivity::class.java)
    }
}