package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.view.BaseActivity
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
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SelectWalletViewModel::class.java)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, SelectWalletActivity::class.java)
    }
}