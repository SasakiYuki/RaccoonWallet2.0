package wallet.raccoon.raccoonwallet.repository

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import wallet.raccoon.raccoonwallet.db.dao.WalletInfoDao
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import javax.inject.Inject

class MyProfileRepository @Inject constructor(
  private val sharedPreferenceUtils: SharedPreferenceUtils,
  private val walletInfoDao: WalletInfoDao
) {

  fun loadMyProfileAsync(): Deferred<MyProfileEntity?> =
    GlobalScope.async {
      sharedPreferenceUtils.myProfile
    }

  fun loadMyProfileSingle(): Single<MyProfileEntity> {
    return Single.create { emitter ->
      emitter.onSuccess(sharedPreferenceUtils.myProfile!!)
    }
  }

  fun updateMyProfile(entity: MyProfileEntity): Single<MyProfileEntity> {
    return Single.create { emitter ->
      sharedPreferenceUtils.myProfile = entity
      emitter.onSuccess(entity)
    }
  }

  fun insertWalletInfoAsync(entity: WalletInfo): Deferred<WalletInfo> =
    GlobalScope.async {
      WalletInfo(
          walletInfoDao.insert(entity),
          entity.walletName,
          entity.walletAddress,
          entity.isMaster
      )
    }

  fun updateWalletInfoAsync(walletInfo: WalletInfo): Deferred<WalletInfo> =
    GlobalScope.async {
      walletInfoDao.update(walletInfo)
      walletInfo
    }

}