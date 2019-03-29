package wallet.raccoon.raccoonwallet.view.activity.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.activity.BaseActivity

class MyAddressProfileActivity : BaseActivity() {
  override fun setLayout() = R.layout.activity_my_address_profile

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, MyAddressProfileActivity::class.java)
    }
  }
}
