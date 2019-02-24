package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_raccoon_user.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.type.TutorialType.RACCOON
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonRaccoonUserFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_raccoon_user

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      replaceFragment(TutorialLessonPrivateKeyDescriptionFragment.newInstance(RACCOON), true)
    }
  }

  companion object {
    fun newInstance(): TutorialLessonRaccoonUserFragment {
      val fragment = TutorialLessonRaccoonUserFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
      }
      return fragment
    }
  }
}