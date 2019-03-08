package wallet.raccoon.raccoonwallet.model.local

import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble

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
) {
  fun getFullName() = mosaicItem.mosaicId.fullName
  fun getMosaicBalance() =
    if (mosaicItem.isNEMXEMItem()) mosaicItem.quantity.convertNEMFromMicroToDouble().toString() else (mosaicItem.quantity / Math.pow(
        10.0, divisibility.toDouble()
    )).toString()

  fun isSelected() = selected
}

