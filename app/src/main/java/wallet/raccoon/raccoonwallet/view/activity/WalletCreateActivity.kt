package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialWalletCreateFragment

class WalletCreateActivity : BaseFragmentActivity() {
  override fun initialFragment() = TutorialWalletCreateFragment.newInstance()

  override fun setLayout() = R.layout.activity_container

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setToolBarBackButton()
  }

  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, WalletCreateActivity::class.java)
    }
  }
}
