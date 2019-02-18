package wallet.raccoon.raccoonwallet.view.fragment.pincode

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_completed_pin_code_setting.lessonEndButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialLessonFinishFragment
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.WalletCreatedType.NEWBIE

class CompletedPinCodeSettingFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_completed_pin_code_setting

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    lessonEndButton.setOnClickListener {
      replaceFragment(TutorialLessonFinishFragment.newInstance(NEWBIE), true)
    }
  }

  companion object {
    fun newInstance(): CompletedPinCodeSettingFragment {
      val fragment = CompletedPinCodeSettingFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.pin_code_setting_end_fragment_title)
      }
      return fragment
    }
  }
}