package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonPinCodeFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_pin_code

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
  }

  companion object {
    fun newInstance(): TutorialLessonPinCodeFragment {
      val fragment = TutorialLessonPinCodeFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_login_description_fragment_title)
      }
      return fragment
    }
  }

}

