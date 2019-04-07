package wallet.raccoon.raccoonwallet.view.activity.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.type.ProfileAddressAddType
import wallet.raccoon.raccoonwallet.view.activity.BaseActivity

class ProfileAddressAddActivity : BaseActivity() {
  override fun setLayout() = R.layout.activity_profile_address_add

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  companion object {
    private const val INTENT_TYPE = "intent_type"
    const val INTENT_WALLET_INFO = "intent_wallet_info"
    private const val ARGS_WALLET_INFO = "args_wallet_info"
    const val REQUEST_CODE = 1010
    fun createIntent(
      context: Context,
      type: ProfileAddressAddType = ProfileAddressAddType.MyProfile,
      walletInfo: WalletInfo? = null
    ): Intent {
      return Intent(context, ProfileAddressAddActivity::class.java).apply {
        putExtra(INTENT_TYPE, type)
        putExtra(ARGS_WALLET_INFO, walletInfo)
      }
    }
  }
}
