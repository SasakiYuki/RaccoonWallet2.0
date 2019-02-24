package wallet.raccoon.raccoonwallet.extentions

import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import wallet.raccoon.raccoonwallet.R

internal fun Context.getDrawable(resourceId: Int): Drawable? {
  return ContextCompat.getDrawable(this, resourceId)
}

fun Context.showToast(textString: String) =
  Toast.makeText(this, textString, Toast.LENGTH_SHORT).show()

fun Context.showToast(textResId: Int) = this.showToast(this.getString(textResId))

fun Context.pasteFromClipBoard(): String {
  val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

  clipboardManager.primaryClip?.let {
    return it.getItemAt(0)
        .text.toString()
  } ?: run {
    this.showToast(R.string.com_paste_error)
    return ""
  }
}

