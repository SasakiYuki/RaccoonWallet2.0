package wallet.raccoon.raccoonwallet.model.local

import java.io.Serializable

data class CalculationMosaicItem(
  val mosaicItem: FullMosaicItem,
  val amount: Double
) : Serializable {
  companion object {
    fun create() = CalculationMosaicItem(FullMosaicItem.create(), 0.0)
  }
}