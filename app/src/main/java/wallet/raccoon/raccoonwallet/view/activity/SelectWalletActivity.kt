package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_select_wallet.*
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.local.SelectWalletItem
import wallet.raccoon.raccoonwallet.view.BaseActivity
import wallet.raccoon.raccoonwallet.view.controller.SelectWalletListController
import wallet.raccoon.raccoonwallet.viewmodel.SelectWalletViewModel
import javax.inject.Inject

class SelectWalletActivity : BaseActivity() {

    private lateinit var viewModel: SelectWalletViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
    }

    private fun setupViews() {
        setToolbarTitle(R.string.select_wallet_activity_title)

        val controller = SelectWalletListController(this)

        walletSelectRecyclerView.layoutManager = LinearLayoutManager(this)
        walletSelectRecyclerView.adapter = controller.adapter

        val list = ArrayList<SelectWalletItem>()
        controller.setData(list)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, SelectWalletActivity::class.java)
    }
}