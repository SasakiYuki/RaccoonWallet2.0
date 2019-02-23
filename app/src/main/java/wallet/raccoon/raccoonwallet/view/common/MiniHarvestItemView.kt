package wallet.raccoon.raccoonwallet.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.ryuta46.nemkotlin.model.HarvestInfo
import kotlinx.android.synthetic.main.view_mini_harvest_item.view.amountTextView
import kotlinx.android.synthetic.main.view_mini_harvest_item.view.dateTimeTextView
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.HarvestItem

class MiniHarvestItemView(
  context: Context?,
  attrs: AttributeSet?,
  defStyleAttr: Int
) : LinearLayout(context, attrs, defStyleAttr) {
  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : this(context, attrs, 0)

  constructor(context: Context?) : this(context, null, 0)

  init {
    View.inflate(context, R.layout.view_mini_harvest_item, this)
  }

  fun setupHarvest(harvestInfo: HarvestInfo) {
    val harvestItem = HarvestItem.convert(harvestInfo)
    dateTimeTextView.text = harvestItem.timeString

    amountTextView.text =
      (harvestItem.totalFee + " " + context.getString(R.string.com_xem_uppercase))
  }
}

