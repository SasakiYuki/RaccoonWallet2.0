package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_message_type_select.cryptImageView
import kotlinx.android.synthetic.main.fragment_message_type_select.cryptLayout
import kotlinx.android.synthetic.main.fragment_message_type_select.cryptTextView
import kotlinx.android.synthetic.main.fragment_message_type_select.descriptionTextView
import kotlinx.android.synthetic.main.fragment_message_type_select.mailImageView
import kotlinx.android.synthetic.main.fragment_message_type_select.mailLayout
import kotlinx.android.synthetic.main.fragment_message_type_select.mailTextView
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class MessageTypeSelectFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_message_type_select

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    mailLayout.setOnClickListener {
      descriptionTextView.setText(R.string.message_type_select_fragment_message_plane)
      mailImageView.setImageDrawable(
          ContextCompat.getDrawable(it.context, R.drawable.ic_mail_black)
      )
      mailTextView.setTextColor(ContextCompat.getColor(it.context, R.color.textBlack))
      cryptImageView.setImageDrawable(
          ContextCompat.getDrawable(it.context, R.drawable.ic_lock_gray)
      )
      cryptTextView.setTextColor(ContextCompat.getColor(it.context, R.color.textGray))
    }

    cryptLayout.setOnClickListener {
      descriptionTextView.setText(R.string.message_type_select_fragment_message_crypt)
      mailImageView.setImageDrawable(
          ContextCompat.getDrawable(it.context, R.drawable.ic_mail_gray)
      )
      mailTextView.setTextColor(ContextCompat.getColor(it.context, R.color.textGray))
      cryptImageView.setImageDrawable(
          ContextCompat.getDrawable(it.context, R.drawable.ic_lock_black)
      )
      cryptTextView.setTextColor(ContextCompat.getColor(it.context, R.color.textBlack))
    }
  }

  companion object {
    fun newInstance(): MessageTypeSelectFragment {
      val fragment = MessageTypeSelectFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}