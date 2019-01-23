package wallet.raccoon.raccoonwallet.repository

import kotlinx.coroutines.Deferred
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import kotlin.coroutines.suspendCoroutine

class MyProfileRepository(
  private val sharedPreferenceUtils: SharedPreferenceUtils
) {

  suspend fun loadMyProfile(): Deferred<MyProfileEntity> = suspendCoroutine {
    sharedPreferenceUtils.myProfile
  }
}