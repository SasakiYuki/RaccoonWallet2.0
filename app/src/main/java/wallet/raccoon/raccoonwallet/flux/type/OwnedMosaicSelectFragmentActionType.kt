package wallet.raccoon.raccoonwallet.flux.type

import com.ryuta46.nemkotlin.model.Mosaic
import com.ryuta46.nemkotlin.model.MosaicDefinitionMetaDataPair

sealed class OwnedMosaicSelectFragmentActionType {
  class OwnedMosaics(val mosaicList: List<Mosaic>) : OwnedMosaicSelectFragmentActionType()
  class NamespaceMosaics(val mosaics: Pair<String, List<MosaicDefinitionMetaDataPair>>) :
      OwnedMosaicSelectFragmentActionType()
}