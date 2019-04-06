package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_tutorial_create_new_wallet.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.util.WalletProvider
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.TutorialCreateNewWalletViewModel
import javax.inject.Inject

class TutorialCreateNewWalletFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TutorialCreateNewWalletViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun layoutRes() = R.layout.fragment_tutorial_create_new_wallet

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TutorialCreateNewWalletViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        editText.setText("")
    }

    override fun onDetach() {
        super.onDetach()
        compositeDisposable.dispose()
    }

    private fun setupViews() {
        termsOfServiceTextView.setOnClickListener {
            val url = "https://raccoonwallet.com/tos_pp/"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(activity, Uri.parse(url))
        }

        button.setOnClickListener {
            showProgress()
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.createAndSaveWallet(editText.text.toString())
            }
        }

        RxTextView.textChanges(editText)
            .map {
                it.isNotEmpty()
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                button.alpha = if (it) 1.0f else 0.5f
                button.isEnabled = it
            }
            .addTo(compositeDisposable)
    }

    private fun setupViewModel() {
        viewModel.createAndInsertWallet.observe(this, Observer { wallet ->
            hideProgress()

            WalletProvider.wallet = wallet
            replaceFragment(
                TutorialWalletAddressDisplayFragment.newInstance(editText.text.toString(), wallet.address), true
            )
        })
    }

    companion object {
        fun newInstance(): TutorialCreateNewWalletFragment {
            val fragment = TutorialCreateNewWalletFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
            }
            return fragment
        }
    }
}