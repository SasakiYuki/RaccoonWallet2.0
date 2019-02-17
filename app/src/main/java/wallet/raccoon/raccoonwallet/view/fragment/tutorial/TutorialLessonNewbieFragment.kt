package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_private_key_description.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonNewbieFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_newbie

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      replaceFragment(TutorialLessonPrivateKeyDescriptionFragment.newInstance(), true)
    }
  }

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