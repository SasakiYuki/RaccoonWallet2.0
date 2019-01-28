package wallet.raccoon.raccoonwallet.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.model.DrawerEntity
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.store.MainActivityStore
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
  private val context: Context,
  private val store: MainActivityStore
) : BaseViewModel() {
  val myProfileData: MutableLiveData<MyProfileEntity> = MutableLiveData()
  val navigationClickEvent: MutableLiveData<DrawerEntity> = MutableLiveData()

  init {
    addDisposable(store.getter.myProfile
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          myProfileData.postValue(it)
        })
  }

  suspend fun loadMyProfile() {
    store.actionCreator.loadMyProfile(context)
  }
}