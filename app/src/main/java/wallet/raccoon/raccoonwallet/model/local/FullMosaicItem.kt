package wallet.raccoon.raccoonwallet.model.local

import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import java.io.Serializable

/**
 * モザイクデータの完全版
 * /account/mosaic/owned
 * と
 * /namespace/mosaic/definition/page
 * を叩きマッチョ合成することにより生成される激重モデル
 * ダイソー店員「（モザイク関連データは）そこに無ければ無いですね」
 */
data class FullMosaicItem(
  val divisibility: Int,
  val mosaicItem: MosaicItem,
  val selected: Boolean
) : Serializable {
  fun getFullName() = mosaicItem.mosaicId.fullName
  fun getMosaicBalance() =
    if (mosaicItem.isNEMXEMItem()) mosaicItem.quantity.convertNEMFromMicroToDouble().toString() else (mosaicItem.quantity / Math.pow(
        10.0, divisibility.toDouble()
    )).toString()

  fun isSelected() = selected

  fun changeSelectedState() = FullMosaicItem(divisibility, mosaicItem, !selected)

  companion object {
    fun create() = FullMosaicItem(0, MosaicItem.createNEMXEMItem(), true)
  }
}

