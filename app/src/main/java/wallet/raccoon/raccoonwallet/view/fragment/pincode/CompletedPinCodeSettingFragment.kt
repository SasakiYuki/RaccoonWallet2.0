package wallet.raccoon.raccoonwallet.view.fragment.pincode

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class CompletedPinCodeSettingFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_completed_pin_code_setting

  companion object {
    fun newInstance(): CompletedPinCodeSettingFragment {
      val fragment = CompletedPinCodeSettingFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.pin_code_setting_end_fragment_title)
      }
      return fragment
    }
  }
}