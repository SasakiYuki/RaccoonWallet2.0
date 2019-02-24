package wallet.raccoon.raccoonwallet.view.fragment.import

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_imported_wallet_naming.submitButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class ImportedWalletNamingFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_imported_wallet_naming

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    submitButton.setOnClickListener {
      replaceFragment(ImportFinishedFragment.newInstance(), true)
    }
  }

  companion object {
    fun newInstance(): ImportedWalletNamingFragment {
      val fragment = ImportedWalletNamingFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.import_wallet_name_fragment_title)
      }
      return fragment
    }
  }
}