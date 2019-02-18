package wallet.raccoon.raccoonwallet.db

import timber.log.Timber

object Database {
    fun <T> query(
        call: T,
        success: ((response: T) -> Unit)?,
        fail: ((throwable: Throwable) -> Unit)? = null
    ) {
        try {
            success?.invoke(call)
        } catch (t: Throwable) {
            Timber.e(t)
            fail?.invoke(t)
        }
    }
}