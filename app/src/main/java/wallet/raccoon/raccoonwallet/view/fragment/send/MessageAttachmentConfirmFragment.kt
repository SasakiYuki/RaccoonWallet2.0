package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_message_type_select.yesButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class MessageAttachmentConfirmFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_message_attachment_confirm

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    yesButton.setOnClickListener {
      replaceFragment(MessageTypeSelectFragment.newInstance(), true)
    }
  }

  companion object {
    fun newInstance(): MessageAttachmentConfirmFragment {
      val fragment = MessageAttachmentConfirmFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}