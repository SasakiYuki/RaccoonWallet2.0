package wallet.raccoon.raccoonwallet.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import wallet.raccoon.raccoonwallet.BuildConfig

class SharedPreferencesService(context: Context) {
    companion object {
        private const val SHARED_PREF_NAME = BuildConfig.APPLICATION_ID + ".preferences"

        private const val ACCOUNT_NAME = "account_name"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    var accountName: String?
        get() = sharedPreferences.getValue(ACCOUNT_NAME)
        set(value) {
            value?.let {
                sharedPreferences.putValue(ACCOUNT_NAME, it)
            } ?: run {
                sharedPreferences.removeValue(ACCOUNT_NAME)
            }
        }

    private fun SharedPreferences.getValue(key: String): String? = this.getString(key, "")

    private fun SharedPreferences.putValue(
        key: String,
        value: String
    ) = this.edit().putString(key, value).apply()

    private fun SharedPreferences.removeValue(key: String) = this.edit().remove(key).apply()
}
