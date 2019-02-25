package wallet.raccoon.raccoonwallet.view.fragment.send

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.AmountInputFragmetViewModel
import javax.inject.Inject

class AmountInputFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: AmountInputFragmetViewModel

  override fun layoutRes() = R.layout.fragment_amount_input

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)

    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AmountInputFragmetViewModel::class.java)
  }

  companion object {
    fun newInstance(): AmountInputFragment {
      val fragment = AmountInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}