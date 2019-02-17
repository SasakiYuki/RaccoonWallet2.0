package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonPrivateKeyDescriptionFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_private_key_description

  companion object {
    fun newInstance(): TutorialLessonPrivateKeyDescriptionFragment {
      val fragment = TutorialLessonPrivateKeyDescriptionFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_description_fragment_title)
      }
      return fragment
    }
  }
}