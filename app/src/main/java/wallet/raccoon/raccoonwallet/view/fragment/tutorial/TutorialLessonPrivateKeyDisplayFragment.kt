package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_for_newbie_pin_code.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.type.TutorialType
import wallet.raccoon.raccoonwallet.type.TutorialType.NEWBIE
import wallet.raccoon.raccoonwallet.type.TutorialType.RACCOON
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonPrivateKeyDisplayFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_private_key_display

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      when (arguments?.getSerializable(
          ARG_TUTORIAL_TYPE
      ) as? TutorialType ?: NEWBIE) {
        NEWBIE ->
          replaceFragment(TutorialLessonForNewbiePinCodeFragment.newInstance(), true)
        RACCOON ->
          replaceFragment(TutorialLessonFinishFragment.newInstance(RACCOON), true)
      }
    }
  }

  companion object {
    private const val ARG_TUTORIAL_TYPE = "tutorial_type"
    fun newInstance(tutorialType: TutorialType): TutorialLessonPrivateKeyDisplayFragment {
      val fragment = TutorialLessonPrivateKeyDisplayFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_description_fragment_title)
        putSerializable(ARG_TUTORIAL_TYPE, tutorialType)
      }
      return fragment
    }
  }
}