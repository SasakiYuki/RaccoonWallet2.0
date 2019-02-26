package wallet.raccoon.raccoonwallet.flux.reducer

import com.ryuta46.nemkotlin.model.Mosaic
import com.ryuta46.nemkotlin.model.MosaicDefinitionMetaDataPair
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.OwnedMosaicSelectFragmentActionType

class OwnedMosaicSelectFragmentReducer(actionType: Observable<OwnedMosaicSelectFragmentActionType>) :
    DisposableMapper() {
  private val mOwnedMosaics: PublishSubject<List<Mosaic>> = PublishSubject.create()
  private val mNamespaceMosaics: PublishSubject<List<MosaicDefinitionMetaDataPair>> =
    PublishSubject.create()

  val ownedMosaics: Observable<List<Mosaic>>
    get() = mOwnedMosaics
  val namespace: Observable<List<MosaicDefinitionMetaDataPair>>
    get() = mNamespaceMosaics

  init {
    actionType.ofType(OwnedMosaicSelectFragmentActionType.OwnedMosaics::class.java)
        .subscribe({
          mOwnedMosaics.onNext(it.mosaicList)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }

    actionType.ofType(OwnedMosaicSelectFragmentActionType.NamespaceMosaics::class.java)
        .subscribe({
          mNamespaceMosaics.onNext(it.mosaics)
        }, {
          it.printStackTrace()
        })
        .let { disposables.add(it) }
  }
}