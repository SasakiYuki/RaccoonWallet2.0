package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletCreateFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_create

  companion object {
    fun newInstance(): TutorialWalletCreateFragment {
      val fragment = TutorialWalletCreateFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
      }
      return fragment
    }
  }
}