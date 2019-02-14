package wallet.raccoon.raccoonwallet.model.local

import java.io.Serializable

data class MosaicIdItem(
  val namespaceId: String,
  val name: String
) : Serializable {
  val fullName: String get() = "$namespaceId:$name"
}
