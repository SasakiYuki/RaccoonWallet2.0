package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.Mosaic
import com.ryuta46.nemkotlin.model.MosaicDefinitionMetaDataPair
import wallet.raccoon.raccoonwallet.flux.store.OwnedMosaicSelectFragmentStore
import javax.inject.Inject

class OwnedMosaicSelectFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: OwnedMosaicSelectFragmentStore
) : BaseViewModel() {
  val ownedMosaicsData: MutableLiveData<List<Mosaic>> = MutableLiveData()
  val namespaceMosaicsData: MutableLiveData<List<MosaicDefinitionMetaDataPair>> = MutableLiveData()

  init {
    addDisposable(store.getter.ownedMosaics
        .subscribe {
          ownedMosaicsData.postValue(it)
        })

    addDisposable(store.getter.namespace
        .subscribe {
          namespaceMosaicsData.postValue(it)
        })
  }

  suspend fun loadOwnedMosaic(address: String) {
    store.actionCreator.loadOwnedMosaics(context, address)
  }

  suspend fun loadNamespaceMosaic(namespace: String) {
    store.actionCreator.loadNamespaceMosaic(context, namespace)
  }
}