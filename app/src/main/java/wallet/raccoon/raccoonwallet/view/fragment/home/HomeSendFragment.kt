package wallet.raccoon.raccoonwallet.view.fragment.home

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class HomeSendFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_home_send

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