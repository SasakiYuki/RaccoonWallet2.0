package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_amount_input.calculator
import kotlinx.android.synthetic.main.view_calculator.view.pagerIndicator
import kotlinx.android.synthetic.main.view_calculator.view.wrapViewPager
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.model.local.MosaicItem
import wallet.raccoon.raccoonwallet.view.adapter.CalculatorPagerAdapter
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.send.SendActivityViewModel

class AmountInputFragment : BaseFragment() {
  private val selectedMosaicItem: ArrayList<FullMosaicItem> = ArrayList()
  override fun layoutRes() = R.layout.fragment_amount_input

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    selectedMosaicItem.add(FullMosaicItem.create())
    activity?.let { fragmentActivity ->
      ViewModelProviders.of(fragmentActivity)
          .get(SendActivityViewModel::class.java)
          .let { viewModel ->
            viewModel
                .mosaicSelectedData.observe(this, Observer { selectedFullMosaicItem ->
              var isMosaicSelected = false
              for (item in selectedMosaicItem) {
                if (item.getFullName() == selectedFullMosaicItem.getFullName()) {
                  isMosaicSelected = true
                }
              }
              if (isMosaicSelected) {
                val filteredMosaics = ArrayList<FullMosaicItem>()
                selectedMosaicItem.filterTo(filteredMosaics) {
                  it.getFullName() != selectedFullMosaicItem.getFullName()
                }
                val mappedMosaics = ArrayList<MosaicItem>()
                filteredMosaics.mapTo(mappedMosaics) {
                  it.mosaicItem
                }
                selectedMosaicItem.clear()
                selectedMosaicItem.addAll(filteredMosaics)
                calculator.removeItem(selectedFullMosaicItem)
                calculator.pagerAdapter = CalculatorPagerAdapter(
                    (context as AppCompatActivity).supportFragmentManager,
                    mappedMosaics
                )
                calculator.setupViewPager()
                calculator.pagerIndicator.setCurrentPosition(0)
                calculator.pagerIndicator.setCount(selectedMosaicItem.count())
                calculator.resetCurrentTexts()
              } else {
                selectedMosaicItem.add(selectedFullMosaicItem.changeSelectedState())
                calculator.pagerAdapter.add(selectedFullMosaicItem.mosaicItem)
                calculator.resetCurrentTexts()
                calculator.pagerIndicator.setCurrentPosition(0)
                calculator.pagerIndicator.setCount(selectedMosaicItem.count())
                calculator.wrapViewPager.currentItem = 0
              }
            })
            viewModel.switchStateData.observe(this, Observer {
              if (it) {
                selectedMosaicItem.add(0, FullMosaicItem.create())
                calculator.pagerAdapter.add(MosaicItem.createNEMXEMItem())
                calculator.resetCurrentTexts()
                calculator.pagerIndicator.setCurrentPosition(0)
                calculator.pagerIndicator.setCount(selectedMosaicItem.count())
                calculator.wrapViewPager.currentItem = 0
              } else {
                val filteredMosaics = ArrayList<FullMosaicItem>()
                selectedMosaicItem.filterTo(filteredMosaics) { mosaicItem ->
                  !mosaicItem.mosaicItem.isNEMXEMItem()
                }
                val mappedMosaics = ArrayList<MosaicItem>()
                filteredMosaics.mapTo(mappedMosaics) {
                  it.mosaicItem
                }
                selectedMosaicItem.clear()
                selectedMosaicItem.addAll(filteredMosaics)
                calculator.removeItem(FullMosaicItem.create())
                calculator.pagerAdapter = CalculatorPagerAdapter(
                    (context as AppCompatActivity).supportFragmentManager,
                    mappedMosaics
                )
                calculator.setupViewPager()
                calculator.pagerIndicator.setCurrentPosition(0)
                calculator.pagerIndicator.setCount(selectedMosaicItem.count())
                calculator.resetCurrentTexts()
              }
            })
          }
    }
  }

  companion object {
    fun newInstance(): AmountInputFragment {
      val fragment = AmountInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}