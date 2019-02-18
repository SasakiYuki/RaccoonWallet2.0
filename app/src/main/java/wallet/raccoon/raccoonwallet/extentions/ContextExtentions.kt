package wallet.raccoon.raccoonwallet.extentions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat

internal fun Context.getDrawable(resourceId: Int): Drawable? {
  return ContextCompat.getDrawable(this, resourceId)
}

fun Context.showToast(textString: String) =
  Toast.makeText(this, textString, Toast.LENGTH_SHORT).show()

fun Context.showToast(textResId: Int) = this.showToast(this.getString(textResId))

