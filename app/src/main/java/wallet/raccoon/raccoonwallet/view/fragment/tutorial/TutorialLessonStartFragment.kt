package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonStartFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_start

  companion object {
    fun newInstance(): TutorialLessonStartFragment {
      val fragment = TutorialLessonStartFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.choose_start_security_activity_title)
      }
      return fragment
    }
  }
}