package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletAddressDisplayFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_address_display

  companion object {
    private const val ARGS_WALLET_NAME = "args_wallet_name"
    fun newInstance(walletName: String): TutorialWalletAddressDisplayFragment {
      val fragment = TutorialWalletAddressDisplayFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
        putString(ARGS_WALLET_NAME, walletName)
      }
      return fragment
    }
  }
}