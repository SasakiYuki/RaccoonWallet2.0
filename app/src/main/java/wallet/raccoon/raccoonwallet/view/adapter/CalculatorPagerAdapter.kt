package wallet.raccoon.raccoonwallet.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nakama.arraypageradapter.ArrayFragmentStatePagerAdapter
import wallet.raccoon.raccoonwallet.model.local.CalculationMosaicItem
import wallet.raccoon.raccoonwallet.model.local.MosaicItem
import wallet.raccoon.raccoonwallet.view.fragment.send.CalculationAmountFragment
import java.util.ArrayList

class CalculatorPagerAdapter(
  fm: FragmentManager,
  mosaics: ArrayList<MosaicItem>
) : ArrayFragmentStatePagerAdapter<MosaicItem>(fm, mosaics) {

  val fragments = ArrayList<Fragment>()

  override fun getFragment(
    item: MosaicItem,
    position: Int
  ): Fragment {
    if (fragments.size < position + 1) {
      val fragment = CalculationAmountFragment.newInstance(item)
      fragments.add(position, fragment)
    }
    return fragments[position]
  }

  fun getCurrentFragment(position: Int): Fragment {
    return fragments[position]
  }

  fun add(
    item: MosaicItem,
    position: Int
  ) {
    fragments.add(position, CalculationAmountFragment.newInstance(item))
  }
}
