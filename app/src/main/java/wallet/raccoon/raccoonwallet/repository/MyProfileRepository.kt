package wallet.raccoon.raccoonwallet.repository

import android.content.Context
import io.reactivex.Single
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils

class MyProfileRepository(private val sharedPreferenceUtils: SharedPreferenceUtils) {
    fun loadMyProfile() = Single.create {
        emitter ->
        sharedPreferenceUtils.
    }
}