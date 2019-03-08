package wallet.raccoon.raccoonwallet.model.local

import com.ryuta46.nemkotlin.model.MosaicId
import java.io.Serializable

data class MosaicIdItem(
  val namespaceId: String,
  val name: String
) : Serializable {
  val fullName: String get() = "$namespaceId:$name"

  companion object {
    fun convert(mosaicId: MosaicId) = MosaicIdItem(mosaicId.namespaceId, mosaicId.name)
  }
}
