package wallet.raccoon.raccoonwallet.view.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class SimpleViewPagerAdapter(val context: Context, val fragments: List<BaseFragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

  override fun getItem(position: Int) = fragments[position]

  override fun getCount() = fragments.size

  override fun getPageTitle(position: Int): CharSequence {
    return if (context.getString(fragments[position].getTitleRes()).isNullOrEmpty()) {
      ""
    } else {
      context.getString(fragments[position].getTitleRes())
    }
  }
}
