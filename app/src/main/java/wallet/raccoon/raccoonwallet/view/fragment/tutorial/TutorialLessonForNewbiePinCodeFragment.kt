package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_for_newbie_pin_code.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.type.TutorialType.NEWBIE
import wallet.raccoon.raccoonwallet.view.activity.PinCodeSettingActivity
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonForNewbiePinCodeFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_for_newbie_pin_code

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      startActivity(PinCodeSettingActivity.createIntent(button.context, NEWBIE))
    }
  }

  companion object {
    fun newInstance(): TutorialLessonForNewbiePinCodeFragment {
      val fragment = TutorialLessonForNewbiePinCodeFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.select_tutorial_level_fragment_login_subtitle)
      }
      return fragment
    }
  }
}