package wallet.raccoon.raccoonwallet.repository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import javax.inject.Inject

class MyProfileRepository @Inject constructor(
    private val sharedPreferenceUtils: SharedPreferenceUtils
) {

    fun loadMyProfile(): Deferred<MyProfileEntity?> =
        GlobalScope.async {
            sharedPreferenceUtils.myProfile
        }
}