package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletCreateFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_create

  companion object {
    fun createIntent(): TutorialWalletCreateFragment {
      val fragment = TutorialWalletCreateFragment()
      return fragment
    }
  }
}