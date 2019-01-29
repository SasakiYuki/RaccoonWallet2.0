package wallet.raccoon.raccoonwallet.view.fragment

import wallet.raccoon.raccoonwallet.R

class DammyFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_dammy

  companion object {
    fun createIntent(): DammyFragment {
      val fragment = DammyFragment()
      return fragment
    }
  }
}