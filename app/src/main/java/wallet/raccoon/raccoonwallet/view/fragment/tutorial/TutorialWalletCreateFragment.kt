package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_wallet_create.createButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletCreateFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_create

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    createButton.setOnClickListener {
      replaceFragment(TutorialCreateNewWalletFragment.newInstance(), true)
    }
  }

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