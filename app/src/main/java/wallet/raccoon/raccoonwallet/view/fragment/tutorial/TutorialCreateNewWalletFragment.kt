package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialCreateNewWalletFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_create_new_wallet


  companion object {
    fun newInstance(): TutorialCreateNewWalletFragment {
      val fragment = TutorialCreateNewWalletFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
      }
      return fragment
    }
  }
}