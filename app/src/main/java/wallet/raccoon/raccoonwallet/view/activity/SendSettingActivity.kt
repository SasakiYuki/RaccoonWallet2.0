package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import wallet.raccoon.raccoonwallet.view.fragment.send.MessageAttachmentConfirmFragment

class SendSettingActivity : BaseFragmentActivity() {
  override fun initialFragment() = MessageAttachmentConfirmFragment.newInstance()

  override fun setLayout() = SIMPLE_FRAGMENT_ONLY_LAYOUT

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setToolBarBackButton()
  }

  companion object {
    fun createIntent(context: Context): Intent {
      val intent = Intent(context, SendSettingActivity::class.java)
      return intent
    }
  }
}
