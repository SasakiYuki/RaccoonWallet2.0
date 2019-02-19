package wallet.raccoon.raccoonwallet.view.fragment.import

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.fragment_import_secret_key.policyButton
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class ImportSecretKeyFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_import_secret_key

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    policyButton.setOnClickListener {
      val url = "https://raccoonwallet.com/tos_pp/"
      val builder = CustomTabsIntent.Builder()
      val customTabsIntent = builder.build()
      customTabsIntent.launchUrl(activity, Uri.parse(url))
    }
  }

  companion object {
    fun newInstance(): ImportSecretKeyFragment {
      val fragment = ImportSecretKeyFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.import_secret_key_fragment_title)
      }
      return fragment
    }
  }
}