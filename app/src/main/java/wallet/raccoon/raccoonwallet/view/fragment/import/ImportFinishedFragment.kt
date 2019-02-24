package wallet.raccoon.raccoonwallet.view.fragment.import

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_import_finished.nextHomeButton
import kotlinx.android.synthetic.main.fragment_import_finished.nextLessonButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialLessonStartFragment

class ImportFinishedFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_import_finished

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    nextLessonButton.setOnClickListener {
      replaceFragment(TutorialLessonStartFragment.newInstance(), true)
    }
    nextHomeButton.setOnClickListener {
      finish()
    }
  }

  companion object {
    fun newInstance(): ImportFinishedFragment {
      val fragment = ImportFinishedFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.complete_import_wallet_activity_title)
      }
      return fragment
    }
  }
}