package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import wallet.raccoon.raccoonwallet.type.TutorialType
import wallet.raccoon.raccoonwallet.view.fragment.pincode.PinCodeInputFragment

class PinCodeSettingActivity : BaseFragmentActivity() {
  override fun initialFragment() = PinCodeInputFragment.newInstance()

  override fun setLayout() = SIMPLE_FRAGMENT_ONLY_LAYOUT

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
  }

  fun getTutorialType() = intent.getSerializableExtra(INTENT_TUTORIAL_TYPE) as TutorialType

  companion object {
    private const val INTENT_TUTORIAL_TYPE = "tutorial_type"
    fun createIntent(
      context: Context,
      tutorialType: TutorialType
    ): Intent {
      val intent = Intent(context, PinCodeSettingActivity::class.java)
      intent.putExtra(INTENT_TUTORIAL_TYPE, tutorialType)
      return intent
    }
  }
}
