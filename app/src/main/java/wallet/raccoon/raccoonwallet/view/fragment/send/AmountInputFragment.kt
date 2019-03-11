package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class AmountInputFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_amount_input

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