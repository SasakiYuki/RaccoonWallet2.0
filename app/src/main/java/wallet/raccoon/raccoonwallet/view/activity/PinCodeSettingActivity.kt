package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import wallet.raccoon.raccoonwallet.view.BaseFragmentActivity
import wallet.raccoon.raccoonwallet.view.fragment.pincode.PinCodeInputFragment

class PinCodeSettingActivity : BaseFragmentActivity() {
  override fun initialFragment() = PinCodeInputFragment.newInstance()

  override fun setLayout() = SIMPLE_FRAGMENT_ONLY_LAYOUT

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
  }

  companion object {
    fun createIntent(context: Context) = Intent(context, PinCodeSettingActivity::class.java)
  }
}
