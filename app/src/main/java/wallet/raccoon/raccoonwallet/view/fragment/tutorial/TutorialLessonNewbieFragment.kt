package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonNewbieFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_newbie

  companion object {
    fun newInstance(): TutorialLessonNewbieFragment {
      val fragment = TutorialLessonNewbieFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_description_fragment_title)
      }
      return fragment
    }
  }
}