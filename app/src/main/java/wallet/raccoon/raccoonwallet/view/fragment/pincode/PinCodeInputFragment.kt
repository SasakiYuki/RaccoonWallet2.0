package wallet.raccoon.raccoonwallet.view.fragment.pincode

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class PinCodeInputFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_pin_code_input

  companion object {
    fun newInstance(): PinCodeInputFragment {
      val fragment = PinCodeInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, DEFAULT_VALUE_VISIBLE_TOOLBAR)
      }
      return fragment
    }
  }
}