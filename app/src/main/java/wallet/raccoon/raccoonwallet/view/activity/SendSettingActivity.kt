package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import wallet.raccoon.raccoonwallet.view.fragment.send.MessageAttachmentConfirmFragment

class SendSettingActivity : BaseFragmentActivity() {
  override fun initialFragment() = MessageAttachmentConfirmFragment.newInstance()

  override fun setLayout() = SIMPLE_FRAGMENT_ONLY_LAYOUT

  companion object {
    fun createIntent(context: Context): Intent {
      val intent = Intent(context, SendSettingActivity::class.java)
      return intent
    }
  }
}
