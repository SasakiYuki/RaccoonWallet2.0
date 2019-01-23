package wallet.raccoon.raccoonwallet.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.store.MainActivityStore

class MainActivityViewModel(
  application: Application,
  private val store: MainActivityStore
) : BaseViewModel(application) {
  val myProfileData: MutableLiveData<MyProfileEntity> = MutableLiveData()

  init {
    addDisposable(store.getter.myProfile
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          myProfileData.postValue(it)
        })
  }

  suspend fun loadMyProfile() {
    store.actionCreator.loadMyProfile(getApplication())
  }
}