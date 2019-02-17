package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_wallet_address_display.button
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonStartFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_lesson_start

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    button.setOnClickListener {
      replaceFragment(TutorialLessonLevelFragment.newInstance(), true)
    }
  }

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