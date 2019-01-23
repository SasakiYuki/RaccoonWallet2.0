package wallet.raccoon.raccoonwallet.util

import android.graphics.Color
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SnackBarUtil {
    fun show(view: View, @StringRes stringRes: Int) {
        val isMainThread = Thread.currentThread()
            .equals(Looper.getMainLooper())
        if (!isMainThread) {
            CoroutineScope(Dispatchers.Main).launch {
                Snackbar.make(view, stringRes, Snackbar.LENGTH_LONG)
                    .apply {
                        setTextColor(Color.WHITE).show()
                    }
            }
        } else {
            Snackbar.make(view, stringRes, Snackbar.LENGTH_LONG)
                .apply {
                    setTextColor(Color.WHITE).show()
                }
        }
    }

    fun show(
        view: View,
        text: String
    ) {
        val isMainThread = Thread.currentThread()
            .equals(Looper.getMainLooper())
        if (!isMainThread) {
            CoroutineScope(Dispatchers.Main).launch {
                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                    .apply {
                        setTextColor(Color.WHITE).show()
                    }
            }
        } else {
            Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .apply {
                    setTextColor(Color.WHITE).show()
                }
        }
    }

    private fun Snackbar.setTextColor(color: Int): Snackbar {
        val tv = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        tv.setTextColor(color)

        return this
    }
}
