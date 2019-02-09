package wallet.raccoon.raccoonwallet.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import kotlinx.android.synthetic.main.view_mini_transaction.view.amountTextView
import kotlinx.android.synthetic.main.view_mini_transaction.view.dateTimeTextView
import kotlinx.android.synthetic.main.view_mini_transaction.view.isMultisigTypeIcon
import kotlinx.android.synthetic.main.view_mini_transaction.view.transactionTypeIcon
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.TransactionItem
import wallet.raccoon.raccoonwallet.type.TransactionType

class MiniTransactionItemView(
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
    View.inflate(context, R.layout.view_mini_transaction, this)
  }

  fun setupTransaction(
    address: String,
    metaDataPair: TransactionMetaDataPair
  ) {
    val transactionItem = TransactionItem.convert(address, metaDataPair)
    dateTimeTextView.text = transactionItem.date
    val transactionType = transactionItem.transactionType
    transactionTypeIcon.setImageDrawable(getTransactionTypeDrawable(transactionItem))
    if (transactionItem.isMultisig) {
      isMultisigTypeIcon.setImageDrawable(
          ContextCompat.getDrawable(
              context,
              if (transactionType == TransactionType.INCOMING) R.mipmap.icon_multisignature_green else R.mipmap.icon_multisignature_red
          )
      )
    } else {
      isMultisigTypeIcon.visibility = View.INVISIBLE
    }
    amountTextView.text =
      (transactionItem.amount + " " + context.getString(R.string.com_xem_uppercase))
  }

  private fun getTransactionTypeDrawable(transactionItem: TransactionItem): Drawable? {
    return if (transactionItem.transactionType == TransactionType.INCOMING) {
      ContextCompat.getDrawable(context, R.mipmap.icon_transaction_receive_green)
    } else {
      ContextCompat.getDrawable(context, R.mipmap.icon_transaction_receive_red)
    }
  }
}
