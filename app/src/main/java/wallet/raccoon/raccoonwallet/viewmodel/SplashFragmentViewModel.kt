package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import wallet.raccoon.raccoonwallet.store.store.SplashFragmentStore
import javax.inject.Inject

class SplashFragmentViewModel @Inject constructor(private val store: SplashFragmentStore, private val context: Context) :
    BaseViewModel() {

    val activeNodeData: MutableLiveData<String> = MutableLiveData()

    init {
        addDisposable(store.getter.activeNode
            .map {
                return@map if (it.https.isNullOrEmpty()) {
                    //httpsのNodeが取得できなかった場合、httpのNodeを返す
                    "http://" + it.http[0] + ":7890"
                } else {
                    it.https[0]
                }
            }
            .subscribe {
                activeNodeData.postValue(it)
            })
    }

    suspend fun loadActiveNode() {
        store.actionCreator.loadActiveNodeList(context)
    }
}