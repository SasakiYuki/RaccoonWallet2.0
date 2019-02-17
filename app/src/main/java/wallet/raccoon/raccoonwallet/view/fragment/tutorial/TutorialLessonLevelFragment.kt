package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_level.newbieButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonLevelFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_level

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    newbieButton.setOnClickListener {
      replaceFragment(TutorialLessonNewbieFragment.newInstance(), true)
    }
  }

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