package wallet.raccoon.raccoonwallet.network

import android.content.Context
import android.view.View
import kotlinx.coroutines.Deferred
import timber.log.Timber
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.util.SnackBarUtil
import wallet.raccoon.raccoonwallet.util.ToastUtil
import java.net.UnknownHostException

object Network {
  suspend fun <T> request(
    rootView: View,
    call: Deferred<T>,
    success: ((response: T) -> Unit)?,
    fail: ((throwable: Throwable) -> Unit)? = null
  ) {
    try {
      success?.invoke(call.await())
    } catch (t: Throwable) {
      Timber.e(t)
      when (t) {
        is UnknownHostException -> {
          SnackBarUtil.show(rootView, R.string.com_network_error)
        }
        else -> {
          fail?.invoke(t)
        }
      }
    }
  }

  suspend fun <T> request(
    context: Context,
    call: Deferred<T>,
    success: ((response: T) -> Unit)?,
    fail: ((throwable: Throwable) -> Unit)? = null
  ) {
    try {
      success?.invoke(call.await())
    } catch (t: Throwable) {
      Timber.e(t)
      when (t) {
        is UnknownHostException -> {
          ToastUtil.show(context, R.string.com_network_error)
        }
        else -> {
          fail?.invoke(t)
        }
      }
    }
  }
}
