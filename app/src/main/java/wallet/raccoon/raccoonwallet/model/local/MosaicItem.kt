package wallet.raccoon.raccoonwallet.model.local

import com.ryuta46.nemkotlin.model.Mosaic
import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import wallet.raccoon.raccoonwallet.util.NEMCommons
import java.io.Serializable

class MosaicItem(
  val mosaicId: MosaicIdItem,
  val quantity: Long
) : Serializable {
  fun getFullName() = mosaicId.fullName
  fun getQuantity() = quantity.toString()
  fun getMosaicBalance() =
    if (isNEMXEMItem()) quantity.convertNEMFromMicroToDouble().toString() else quantity.toString()

  fun isNEMXEMItem() =
    mosaicId.namespaceId == NEMCommons.DEFAULT_NEM_NAMESPACE && mosaicId.name == NEMCommons.DEFAULT_NEM_NAME

  companion object {
    fun createNEMXEMItem() =
      MosaicItem(MosaicIdItem(NEMCommons.DEFAULT_NEM_NAMESPACE, NEMCommons.DEFAULT_NEM_NAME), 0L)

    fun convert(mosaic: Mosaic) =
      MosaicItem(MosaicIdItem.convert(mosaic.mosaicId), mosaic.quantity)
  }
}

