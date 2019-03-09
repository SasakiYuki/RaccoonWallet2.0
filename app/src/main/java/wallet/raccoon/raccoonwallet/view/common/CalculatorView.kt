package wallet.raccoon.raccoonwallet.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_calculator.view.btnCE
import kotlinx.android.synthetic.main.view_calculator.view.btnZero
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.util.ToastUtil

class CalculatorView(
  context: Context?,
  attrs: AttributeSet?,
  defStyleAttr: Int
) :
    LinearLayout(context, attrs, defStyleAttr) {
  private var numberText = ""
  private val numbersList = ArrayList<Double>()
  private val operationsList = ArrayList<Char>()

  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : this(context, attrs, 0)

  constructor(context: Context?) : this(context, null, 0)

  init {
    View.inflate(context, R.layout.view_calculator, this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    btnZero.setOnClickListener {

    }
    btnCE.setOnClickListener {
      ToastUtil.show(context, R.string.select_wallet_activity_title)
    }
  }
}