package wallet.raccoon.raccoonwallet.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import wallet.raccoon.raccoonwallet.view.fragment.DammyFragment
import wallet.raccoon.raccoonwallet.view.fragment.home.HomeFragment

class TopFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
  private val list = ArrayList<Fragment>()

  init {
    list.add(DammyFragment.createIntent())
    list.add(DammyFragment.createIntent())
    list.add(HomeFragment.newInstance())
    list.add(DammyFragment.createIntent())
    list.add(DammyFragment.createIntent())
  }

  override fun getItem(position: Int): Fragment {
    return list[position]
  }

  override fun getCount(): Int {
    return list.size
  }
}