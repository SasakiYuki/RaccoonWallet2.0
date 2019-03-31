package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_tutorial_wallet_address_display.*
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.extentions.toDisplayAddress
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialWalletAddressDisplayFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_tutorial_wallet_address_display

  private val address by lazy {
    arguments?.getString(ARGS_WALLET_ADDRESS)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    button.setOnClickListener {
      replaceFragment(TutorialWalletCreatedFragment.newInstance(), true)
    }

    publicAddressTextView.text = address?.toDisplayAddress()
  }

  companion object {
    private const val ARGS_WALLET_NAME = "args_wallet_name"
    private const val ARGS_WALLET_ADDRESS = "args_wallet_address"

    fun newInstance(walletName: String, walletAddress: String): TutorialWalletAddressDisplayFragment {
      val fragment = TutorialWalletAddressDisplayFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.create_wallet_tutorial_title)
        putString(ARGS_WALLET_NAME, walletName)
        putString(ARGS_WALLET_ADDRESS, walletAddress)
      }
      return fragment
    }
  }
}