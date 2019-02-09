package wallet.raccoon.raccoonwallet.model.local

import wallet.raccoon.raccoonwallet.type.TransactionType
import java.io.Serializable

data class TransactionItem(
  val transactionType: TransactionType,
  val block: Int?,
  val hash: String?,
  val date: String?,
  val fee: String?,
  val amount: String?,
  val signer: String?,
  var senderAddress: String?,
  val recipientAddress: String?,
  val isMultisig: Boolean,
  val message: String?,
  val timeStamp: Long,
  val mosaicList: ArrayList<MosaicItem>,
  val messageType: Int?,
  val transactionId: Int = 0
) : Serializable
