package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_calculation_amount.mosaicCountText
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.MosaicItem
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class CalculationAmountFragment : BaseFragment() {
  override fun layoutRes() = R.layout.fragment_calculation_amount

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    val mosaicItem = arguments?.get(ARGS_MOSAIC) as MosaicItem
    mosaicCountText.text = if (mosaicItem.isNEMXEMItem()) getText(
        R.string.com_xem_uppercase
    ) else mosaicItem.getFullName()
  }

  private fun replaceMessageAttachmentConfirmFragment() {
  }

  companion object {
    private val ARGS_MOSAIC = "args_mosaic"
    fun newInstance(mosaicItem: MosaicItem): CalculationAmountFragment {
      val fragment = CalculationAmountFragment()
      fragment.arguments = Bundle().apply {
        putSerializable(ARGS_MOSAIC, mosaicItem)
      }
      return fragment
    }
  }
}