package wallet.raccoon.raccoonwallet.model.local

import com.ryuta46.nemkotlin.model.MosaicId
import java.io.Serializable

class MosaicItem(
  mosaicId: MosaicId,
  val quantity: Long
) : Serializable {
  var mosaicIdAppEntity = MosaicIdItem(
      namespaceId = mosaicId.namespaceId,
      name = mosaicId.name
  )
}

