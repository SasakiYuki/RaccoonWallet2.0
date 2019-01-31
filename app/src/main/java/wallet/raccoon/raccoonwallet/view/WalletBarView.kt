package wallet.raccoon.raccoonwallet.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import wallet.raccoon.raccoonwallet.R

class WalletBarView(
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
    View.inflate(context, R.layout.view_wallet_bar, this)
  }
}