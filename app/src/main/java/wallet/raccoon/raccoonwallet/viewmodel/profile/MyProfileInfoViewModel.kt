package wallet.raccoon.raccoonwallet.viewmodel.profile

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.flux.store.MyProfileInfoStore
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.viewmodel.BaseViewModel
import javax.inject.Inject

class MyProfileInfoViewModel @Inject constructor(private val store: MyProfileInfoStore)
  : BaseViewModel() {
  val myAddressCountLiveData: MutableLiveData<Int>
      = MutableLiveData()
  val myProfileEntityLiveData: MutableLiveData<MyProfileEntity>
      = MutableLiveData()
  val addressLiveData: MutableLiveData<String>
      = MutableLiveData()
  val createEventLiveData: MutableLiveData<Unit>
      = MutableLiveData()
  val updateEventLiveData: MutableLiveData<MyProfileEntity>
      = MutableLiveData()
  val bottomEditButtonEventLiveData: MutableLiveData<Unit>
      = MutableLiveData()
  val bottomCompleteButtonEventLiveData: MutableLiveData<Unit>
      = MutableLiveData()
  val screenChangeEventLiveData: MutableLiveData<Unit>
      = MutableLiveData()

  init {
    store.getter.myAddressObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          myAddressCountLiveData.value = it
        }.let {
          addDisposable(it)
        }
    store.getter.myProfileEntityObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          myProfileEntityLiveData.value = it
        }.let {
          addDisposable(it)
        }
    store.getter.updateObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          updateEventLiveData.value = it
        }.let {
          addDisposable(it)
        }
//    RxBus.receive(MyAddressProfileBottomButtonEvent::class.java)
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe {
//          if (it is MyAddressProfileBottomButtonEvent.OnClickCompleteBottomButton) {
//            bottomCompleteButtonEventLiveData.value = Unit
//          } else if (it is MyAddressProfileBottomButtonEvent.OnClickEditBottomButton) {
//            bottomEditButtonEventLiveData.value = Unit
//          } else if (it is MyAddressProfileBottomButtonEvent.OnChangeEditBottomButton) {
//            screenChangeEventLiveData.value = Unit
//          }
//        }.let { addDisposable(it) }
//    RxBus.receive(MasterWalletInfoEvent::class.java)
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe {
//          addressLiveData.value = it.walletInfo.walletAddress
//        }
  }

  private fun countUpMyAddress() {
    store.actionCreator.countUpMyAddress()
  }

  private fun loadMyProfile() {
    store.actionCreator.loadMyProfile()
  }

  fun onInit() {
    countUpMyAddress()
    loadMyProfile()
  }

  fun update(myProfileEntity: MyProfileEntity) {
    store.actionCreator.updateMyProfile(myProfileEntity)
  }
}
