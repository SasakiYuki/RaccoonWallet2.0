package wallet.raccoon.raccoonwallet.view.fragment

import wallet.raccoon.raccoonwallet.R

class HomeFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_home

  companion object {
    fun newInstance(): HomeFragment {
      val fragment = HomeFragment()
      return fragment
    }
  }
}