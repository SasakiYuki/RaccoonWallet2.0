package wallet.raccoon.raccoonwallet.util

import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ToastUtil {
    fun show(context: Context, @StringRes stringRes: Int) {
        val isMainThread = Thread.currentThread()
            .equals(Looper.getMainLooper())
        if (!isMainThread) {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, stringRes, Snackbar.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(context, stringRes, Snackbar.LENGTH_LONG)
                .show()
        }
    }
}
