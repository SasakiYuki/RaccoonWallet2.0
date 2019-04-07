package wallet.raccoon.raccoonwallet.viewmodel.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.flux.store.ProfileAddressAddStore
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.viewmodel.BaseViewModel
import javax.inject.Inject

class ProfileAddressAddViewModel @Inject constructor(
  private val context: Context,
  private val store: ProfileAddressAddStore
) : BaseViewModel() {
  val insertLiveData: MutableLiveData<WalletInfo> = MutableLiveData()
  val updateLiveData: MutableLiveData<WalletInfo> = MutableLiveData()
  val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
  var isMaster = false

  init {
    store.getter.insertObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          insertLiveData.value = it
//          RxBus.send(WalletInfoEvent.InsertWalletInfo(it))
        })
        .let { addDisposable(it) }
    store.getter.updateObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          updateLiveData.value = it
//          RxBus.send(WalletInfoEvent.UpdateWalletInfo(it))
        })
    store.getter.errorObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          errorLiveData.value = it
        })
  }

  suspend fun insert(walletInfo: WalletInfo) {
    store.actionCreator.insert(context, walletInfo)
  }

  suspend fun update(walletInfo: WalletInfo) {
    store.actionCreator.update(context, walletInfo)
  }

  override fun onCleared() {
    super.onCleared()
    store.clearDisposables()
  }
}
