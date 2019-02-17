package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonPinCodeFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_pin_code

  companion object {
    fun newInstance(): TutorialLessonPinCodeFragment {
      val fragment = TutorialLessonPinCodeFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.select_tutorial_level_fragment_login_subtitle)
      }
      return fragment
    }
  }
}