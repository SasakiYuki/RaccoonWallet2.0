package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_tutorial_create_new_wallet.*
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
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

        termsOfServiceTextView.setOnClickListener {
            val url = "https://raccoonwallet.com/tos_pp/"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(activity, Uri.parse(url))
        }

        button.setOnClickListener {
            replaceFragment(
                TutorialWalletAddressDisplayFragment.newInstance(editText.text.toString()), true
            )
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

    override fun onResume() {
        super.onResume()
        editText.setText("")
    }

    override fun onDetach() {
        super.onDetach()
        compositeDisposable.dispose()
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