package wallet.raccoon.raccoonwallet.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DialogPagerAdapter(
  val fragments: List<Fragment>,
  fm: FragmentManager
) : FragmentPagerAdapter(fm) {

  override fun getCount(): Int = fragments.size

  override fun getItem(position: Int): Fragment {
    return fragments[position]
  }
}
