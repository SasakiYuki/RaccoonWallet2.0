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
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.model.local.SelectWalletItem
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

        viewModel.selectedWalletIdData.observe(this, Observer { selectedWalletId ->
            viewModel.allWalletsData.observe(this, Observer { wallets ->
                controller.setData(
                    wallets.map { wallet ->
                        SelectWalletItem(wallet = wallet, isSelected = wallet.id == selectedWalletId)
                    }
                )
                loadAccountInfo(wallets)
            })
        })

        viewModel.navigationCreateWalletClickEvent.observe(this, Observer {
            startActivity(WalletCreateActivity.createIntent(this))
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

        viewModel.accountInfoData.observe(this, Observer { accountPair ->
            if (!accountPair.meta.cosignatories.isNullOrEmpty()) {
                controller.currentData?.let { list ->
                    val wallet = list
                        .asSequence()
                        .filter { it.wallet.address == accountPair.account.address }
                        .single()

                    updateWallet(wallet)
                }
            }
        })

        viewModel.updateWallet.observe(this, Observer { walletId ->
            controller.currentData?.let { items ->
                controller.setData(
                    items.map { item ->
                        val wallet = Wallet(
                            id = item.wallet.id,
                            salt = item.wallet.salt,
                            publicKey = item.wallet.publicKey,
                            address = item.wallet.address,
                            encryptedSecretKey = item.wallet.encryptedSecretKey,
                            name = item.wallet.name,
                            isMultisig = item.wallet.id == walletId
                        )
                        SelectWalletItem(wallet = wallet, isSelected = item.isSelected)
                    }
                )
            }
        })
    }

    private fun updateWallet(walletItem: SelectWalletItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val wallet = Wallet(
                id = walletItem.wallet.id,
                salt = walletItem.wallet.salt,
                name = walletItem.wallet.name,
                publicKey = walletItem.wallet.publicKey,
                encryptedSecretKey = walletItem.wallet.encryptedSecretKey,
                address = walletItem.wallet.address,
                isMultisig = true
            )

            viewModel.updateWallet(wallet)
        }
    }

    private fun loadWallets() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadSelectedWalletId()
            viewModel.loadAllWallets()
        }
    }

    private fun loadAccountInfo(list: List<Wallet>) {
        CoroutineScope(Dispatchers.IO).launch {
            val distinctList = list.filter { !it.isMultisig }

            for (item in distinctList) {
                viewModel.loadAccountInfo(item.address)
            }
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