package wallet.raccoon.raccoonwallet.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_wallet_bar.view.fab
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.WalletCreateActivity

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

  override fun onFinishInflate() {
    super.onFinishInflate()
    fab.setOnClickListener {
      context.startActivity(
          WalletCreateActivity.createIntent(context)
      )
    }
  }
}
