package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_private_key_display.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.type.TutorialType
import wallet.raccoon.raccoonwallet.type.TutorialType.NEWBIE
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonPrivateKeyDescriptionFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_private_key_description

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      replaceFragment(
          TutorialLessonPrivateKeyDisplayFragment.newInstance(
              arguments?.getSerializable(
                  ARG_TUTORIAL_TYPE
              ) as? TutorialType ?: NEWBIE
          ), true
      )
    }
  }

  companion object {
    private const val ARG_TUTORIAL_TYPE = "tutorial_type"
    fun newInstance(tutorialType: TutorialType): TutorialLessonPrivateKeyDescriptionFragment {
      val fragment = TutorialLessonPrivateKeyDescriptionFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_description_fragment_title)
        putSerializable(ARG_TUTORIAL_TYPE, tutorialType)
      }
      return fragment
    }
  }
}