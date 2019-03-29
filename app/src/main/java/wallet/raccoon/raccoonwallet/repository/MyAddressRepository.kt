package wallet.raccoon.raccoonwallet.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import wallet.raccoon.raccoonwallet.db.dao.MyAddressDao
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import javax.inject.Inject

class MyAddressRepository @Inject constructor(private val myAddressDao: MyAddressDao) {

  fun insert(myAddress: MyAddress): Completable {
    return Completable.fromAction {
      myAddressDao.insert(myAddress)
    }
  }

  fun findAllMyAddress(): Observable<MyAddress> {
    return Observable.create {
      val myAddress = myAddressDao.findAll()
      for (item in myAddress) {
        it.onNext(item)
      }
    }
  }

  fun countAllMyAddress(): Single<Int> {
    return Single.create {
      it.onSuccess(myAddressDao.findAll().size)
    }
  }

  fun delete(myAddress: MyAddress): Single<MyAddress> {
    return Single.create {
      myAddressDao.delete(myAddress)
      it.onSuccess(myAddress)
    }
  }
}
