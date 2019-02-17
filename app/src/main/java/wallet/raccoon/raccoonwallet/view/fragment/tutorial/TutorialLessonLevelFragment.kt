package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonLevelFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_level

  companion object {
    fun newInstance(): TutorialLessonLevelFragment {
      val fragment = TutorialLessonLevelFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, DEFAULT_VALUE_VISIBLE_TOOLBAR)
      }
      return fragment
    }
  }
}