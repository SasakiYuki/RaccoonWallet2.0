package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class SendMessageInputFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_send_message_input

  companion object {
    fun newInstance(): SendMessageInputFragment {
      val fragment = SendMessageInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, DEFAULT_VALUE_VISIBLE_TOOLBAR)
      }
      return fragment
    }
  }
}