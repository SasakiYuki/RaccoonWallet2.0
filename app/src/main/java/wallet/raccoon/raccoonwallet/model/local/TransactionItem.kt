package wallet.raccoon.raccoonwallet.model.local

import com.ryuta46.nemkotlin.enums.MessageType
import com.ryuta46.nemkotlin.model.GeneralTransaction
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import com.ryuta46.nemkotlin.util.ConvertUtils
import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import wallet.raccoon.raccoonwallet.extentions.getNemStartDateTimeLong
import wallet.raccoon.raccoonwallet.extentions.toDisplayAddress
import wallet.raccoon.raccoonwallet.type.TransactionType
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

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
) : Serializable {
  companion object {
    fun convert(
      myAddress: String,
      it: TransactionMetaDataPair
    ) = TransactionItem(
        isInComing(myAddress, it),
        it.meta.height,
        it.meta.hash.data,
        getInstantDate(it.transaction),
        getFee(it.transaction),
        getAmount(it.transaction),
        it.transaction.signer,
        "",
        getRecipientAddress(it.transaction),
        isMultisig(it.transaction),
        getMessage(it.transaction),
        getTimeStamp(it.transaction),
        getMosaicList(it.transaction),
        getMessageType(it.transaction),
        getTransactionId(it)
    )

    private fun isInComing(
      myAddress: String,
      transactionMetaDataPair: TransactionMetaDataPair
    ): TransactionType {
      return if (myAddress == transactionMetaDataPair.transaction.recipient) TransactionType.INCOMING else TransactionType.OUTGOING
    }

    private fun getInstantDate(generalTransaction: GeneralTransaction): String {
      val nemStartTimeLong = getNemStartDateTimeLong()
      val sdf = SimpleDateFormat("MM/dd yyyy")
      return if (isMultisig(generalTransaction)) {
        val multisigTransaction = getMultisigTransaction(generalTransaction)!!.otherTrans
        val date = Date(multisigTransaction.timeStamp.toLong() * 1000 + nemStartTimeLong)
        sdf.format(date)
      } else {
        val date = Date(generalTransaction.timeStamp.toLong() * 1000 + nemStartTimeLong)
        sdf.format(date)
      }
    }

    private fun isMultisig(generalTransaction: GeneralTransaction): Boolean {
      val item = generalTransaction.asMultisig
      return item != null
    }

    private fun getMultisigTransaction(generalTransaction: GeneralTransaction) =
      generalTransaction.asMultisig

    private fun getMessageType(generalTransaction: GeneralTransaction): Int? {
      return generalTransaction.message?.let {
        it.type
      } ?: run {
        null
      }
    }

    private fun getTransactionId(transactionMetaDataPair: TransactionMetaDataPair) =
      transactionMetaDataPair.meta.id

    private fun getFee(generalTransaction: GeneralTransaction): String? {
      if (isMultisig(generalTransaction)) {
        val multisigTransaction = getMultisigTransaction(generalTransaction)!!.otherTrans
        multisigTransaction.fee?.let {
          return it.convertNEMFromMicroToDouble()
              .toString()
        }
        return null
      } else {
        generalTransaction.fee?.let {
          return it.convertNEMFromMicroToDouble()
              .toString()
        }
        return null
      }
    }

    private fun getAmount(generalTransaction: GeneralTransaction): String? {
      if (isMultisig(generalTransaction)) {
        val multisigTransaction = getMultisigTransaction(generalTransaction)!!.otherTrans
        multisigTransaction.amount?.let {
          return it.convertNEMFromMicroToDouble()
              .toString()
        }
        return null
      } else {
        generalTransaction.amount?.let {
          return it.convertNEMFromMicroToDouble()
              .toString()
        }
        return null
      }
    }

    private fun getRecipientAddress(generalTransaction: GeneralTransaction): String? {
      if (isMultisig(generalTransaction)) {
        val multisigTransaction = getMultisigTransaction(generalTransaction)!!.otherTrans
        multisigTransaction.recipient?.let {
          return it.toDisplayAddress()
        }
        return null
      } else {
        generalTransaction.recipient?.let {
          return it.toDisplayAddress()
        }
        return null
      }
    }

    private fun getMessage(generalTransaction: GeneralTransaction): String? {
      val message = generalTransaction.message
      return if (message != null) {
        if (message.type == MessageType.Plain.rawValue) {
          String(ConvertUtils.toByteArray(message.payload), Charsets.UTF_8)
        } else {
          message.payload
        }
      } else {
        ""
      }
    }

    private fun getMosaicList(generalTransaction: GeneralTransaction): ArrayList<MosaicItem> {
      return generalTransaction.mosaics?.let {
        Observable.fromIterable(it)
            .map { mosaic ->
              MosaicItem(
                  mosaicId = MosaicIdItem.convert(mosaic.mosaicId),
                  quantity = mosaic.quantity
              )
            }
            .toList()
            .blockingGet() as ArrayList<MosaicItem>
      } ?: kotlin.run {
        return ArrayList()
      }
    }

    private fun getTimeStamp(generalTransaction: GeneralTransaction): Long {
      if (isMultisig(generalTransaction)) {
        return getMultisigTransaction(generalTransaction)!!.otherTrans.timeStamp.toLong()
      } else {
        return generalTransaction.timeStamp.toLong()
      }
    }
  }
}
