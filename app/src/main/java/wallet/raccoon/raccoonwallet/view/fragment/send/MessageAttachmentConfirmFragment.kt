package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class MessageAttachmentConfirmFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_message_attachment_confirm

  companion object {
    fun newInstance(): MessageAttachmentConfirmFragment{
      val fragment = MessageAttachmentConfirmFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}