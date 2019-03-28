package wallet.raccoon.raccoonwallet.model.local

import java.io.Serializable
import java.text.NumberFormat

class SendMosaicItem(
  mosaicItem: MosaicItem,
  val amount: Double
) : Serializable {
  private val name: String
  private val namespaceId: String
  private val quantity: String

  init {
    quantity = mosaicItem.getQuantity()
    name = mosaicItem.mosaicId.name
    namespaceId = mosaicItem.mosaicId.namespaceId
  }

  fun mosaicName() = name
  fun nameSpace() = namespaceId
  private fun getFullName() = "$namespaceId:$name"
  fun getFormattedAmount() = NumberFormat.getNumberInstance().format(amount) + " " + getFullName()

  companion object {
    fun createNEMXEMItem(amount: Double) =
      SendMosaicItem(MosaicItem(MosaicIdItem("nem", "xem"), 0), amount)
  }
}
