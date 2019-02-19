package wallet.raccoon.raccoonwallet.view.fragment.import

import android.os.Bundle
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class ImportedWalletNamingFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_imported_wallet_naming

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