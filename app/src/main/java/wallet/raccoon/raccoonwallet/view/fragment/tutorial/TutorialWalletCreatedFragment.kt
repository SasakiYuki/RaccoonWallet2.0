package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_wallet_created.securityButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletCreatedFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_created

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    securityButton.setOnClickListener {
      replaceFragment(TutorialLessonStartFragment.newInstance(), true)
    }
  }

  companion object {
    fun newInstance(): TutorialWalletCreatedFragment {
      val fragment = TutorialWalletCreatedFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
      }
      return fragment
    }
  }
}