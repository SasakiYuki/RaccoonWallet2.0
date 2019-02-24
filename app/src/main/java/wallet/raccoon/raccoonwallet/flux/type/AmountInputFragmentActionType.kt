package wallet.raccoon.raccoonwallet.flux.type

import com.ryuta46.nemkotlin.model.Mosaic
import com.ryuta46.nemkotlin.model.MosaicDefinitionMetaDataPair

sealed class AmountInputFragmentActionType {
  class OwnedMosaics(val mosaicList: List<Mosaic>) : AmountInputFragmentActionType()
  class NamespaceMosaics(val mosaics: List<MosaicDefinitionMetaDataPair>) :
      AmountInputFragmentActionType()
}