package wallet.raccoon.raccoonwallet.viewmodel

import androidx.lifecycle.MutableLiveData
import wallet.raccoon.raccoonwallet.flux.store.TutorialLessonPrivateKeyDisplayStore
import javax.inject.Inject

class TutorialLessonPrivateKeyDisplayViewModel @Inject constructor(private val store: TutorialLessonPrivateKeyDisplayStore) :
    BaseViewModel() {

    val decryptPrivateKey: MutableLiveData<String> = MutableLiveData()

    init {
        addDisposable(store.getter.privateKey
            .subscribe {
                decryptPrivateKey.postValue(it)
            })
    }

    suspend fun decryptPrivateKey(walletId: Long, pin: ByteArray) {
        store.actionCreator.decryptPrivateKey(walletId, pin)
    }
}
