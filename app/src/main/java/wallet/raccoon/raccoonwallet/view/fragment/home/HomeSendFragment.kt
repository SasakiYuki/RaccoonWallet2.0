package wallet.raccoon.raccoonwallet.view.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_home_send.addressEditText
import kotlinx.android.synthetic.main.fragment_home_send.button
import kotlinx.android.synthetic.main.fragment_home_send.clearButton
import kotlinx.android.synthetic.main.fragment_home_send.clipButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.extentions.isNotTextEmptyObservable
import wallet.raccoon.raccoonwallet.extentions.pasteFromClipBoard
import wallet.raccoon.raccoonwallet.extentions.remove
import wallet.raccoon.raccoonwallet.util.ToastUtil
import wallet.raccoon.raccoonwallet.view.activity.SendActivity
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.HomeSendFragmentViewModel
import javax.inject.Inject

class HomeSendFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: HomeSendFragmentViewModel

  override fun layoutRes() = R.layout.fragment_home_send

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)

    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(HomeSendFragmentViewModel::class.java)
  }

  @SuppressLint("CheckResult")
  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.accountInfoData.observe(this, Observer {
      ToastUtil.show(activity!!, R.string.app_name)
      startActivity(SendActivity.createIntent(context!!))
    })

    clipButton.setOnClickListener {
      addressEditText.setText(clipButton.context.pasteFromClipBoard())
    }

    clearButton.setOnClickListener {
      addressEditText.setText("")
    }

    addressEditText.isNotTextEmptyObservable()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          if (it) {
            clipButton.visibility = View.GONE
            clearButton.visibility = View.VISIBLE
          } else {
            clipButton.visibility = View.VISIBLE
            clearButton.visibility = View.GONE
          }
        }

    button.setOnClickListener {
      val address = addressEditText.text.toString()
          .remove("-")

      if (address.isEmpty()) return@setOnClickListener

      CoroutineScope(Dispatchers.IO).launch {
        viewModel.loadAccountInfo(address)
      }
    }
  }

  companion object {
    fun newInstance(): HomeSendFragment {
      val fragment = HomeSendFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.select_wallet_activity_title)
      }
      return fragment
    }
  }
}