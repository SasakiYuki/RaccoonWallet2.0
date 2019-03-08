package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ryuta46.nemkotlin.model.Mosaic
import kotlinx.coroutines.Deferred
import wallet.raccoon.raccoonwallet.flux.store.OwnedMosaicSelectFragmentStore
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.model.local.MosaicItem
import javax.inject.Inject

class OwnedMosaicSelectFragmentViewModel @Inject constructor(
  private val context: Context,
  private val store: OwnedMosaicSelectFragmentStore
) : BaseViewModel() {
  val ownedMosaicsData: MutableLiveData<List<Mosaic>> = MutableLiveData()
  val fullMosaicItemData: MutableLiveData<FullMosaicItem> = MutableLiveData()
  val namespaceData: MutableLiveData<Set<String>> = MutableLiveData()
  private val namespaceHashMapData: MutableLiveData<HashMap<String, List<Mosaic>>> = MutableLiveData()

  init {
    addDisposable(store.getter.ownedMosaics
        .subscribe {
          ownedMosaicsData.postValue(it)
          val nameSpaceHashMap = HashMap<String, List<Mosaic>>()
          it
              .map { mosaic ->
                MosaicItem.convert(mosaic)
              }
              .filter { mosaicItem -> mosaicItem.isNEMXEMItem() }
              .forEach { mosaicItem ->
                fullMosaicItemData.postValue(
                    FullMosaicItem(0, mosaicItem, false)
                )
              }
          for (namespaceMosaic in it) {
            val namespaceList = ArrayList<Mosaic>()
            it.filterTo(
                namespaceList
            ) { mosaic ->
              namespaceMosaic.mosaicId.namespaceId == mosaic.mosaicId.namespaceId
            }
            nameSpaceHashMap[namespaceMosaic.mosaicId.namespaceId] = namespaceList
          }
          namespaceHashMapData.postValue(nameSpaceHashMap)
          namespaceData.postValue(nameSpaceHashMap.keys)
        })

    addDisposable(store.getter.namespace
        .subscribe {
          namespaceHashMapData.value?.let { namespaceHashMap ->
            val mosaicList = namespaceHashMap[it.first]
            mosaicList?.let { mosaics ->
              for (responseItem in it.second) {
                mosaics.filter {
                  responseItem.mosaic.id.fullName == it.mosaicId.fullName
                }
                    .forEach { item ->
                      responseItem.mosaic.divisibility?.let { divisibility ->
                        fullMosaicItemData.postValue(
                            FullMosaicItem(
                                divisibility,
                                MosaicItem.convert(item),
                                false
                            )
                        )
                      }
                    }
              }
            }
          }
        })
  }

  suspend fun loadOwnedMosaic(address: String) {
    store.actionCreator.loadOwnedMosaics(context, address)
  }

  suspend fun loadNamespaceMosaic(namespace: String): Deferred<Boolean> {
    return store.actionCreator.loadNamespaceMosaic(context, namespace)
  }
}