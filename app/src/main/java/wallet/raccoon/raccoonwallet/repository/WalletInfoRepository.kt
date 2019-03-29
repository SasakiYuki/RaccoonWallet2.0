package wallet.raccoon.raccoonwallet.repository

import io.reactivex.Completable
import io.reactivex.Single
import wallet.raccoon.raccoonwallet.db.dao.WalletInfoDao
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import javax.inject.Inject

class WalletInfoRepository @Inject constructor(private val walletInfoDao: WalletInfoDao) {

  fun insert(entity: WalletInfo): Single<WalletInfo> {
    return Single.create { emitter ->
      entity.let {
        val id = walletInfoDao.insert(it)
        WalletInfo(
            id,
            it.walletName,
            it.walletAddress,
            it.isMaster
        ).let {
          emitter.onSuccess(it)
        }
      }
    }
  }

  fun select(id: Long): Single<WalletInfo> {
    return Single.create {
      it.onSuccess(walletInfoDao.select(id))
    }
  }

  fun remove(walletInfo: WalletInfo): Completable {
    return Completable.fromAction {
      walletInfoDao.delete(walletInfo)
    }
  }
}
