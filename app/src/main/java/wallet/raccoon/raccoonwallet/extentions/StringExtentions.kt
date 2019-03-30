package wallet.raccoon.raccoonwallet.extentions

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import java.util.regex.Pattern

fun String.toDisplayAddress(): String {
  val matcher = Pattern.compile("[\\s\\S]{1,6}")
      .matcher(this)

  var address = ""
  while (matcher.find()) {
    val text = matcher.group()

    if (text.length == 4) {
      address += text
    } else {
      address += text + "-"
    }
  }
  return address
}

fun String.remove(target: String): String {
  val charArray = this.toCharArray()
  val stringBuilder = StringBuilder()
  charArray
      .filter { it != target.single() }
      .forEach { stringBuilder.append(it) }
  return String(stringBuilder)
}

fun String.copyClipBoard(context: Context) {
  val item = ClipData.Item(this)

  val mimeType = arrayOfNulls<String>(1)
  mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST

  val cd = ClipData(ClipDescription("text_data", mimeType), item)

  val cm = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
  cm.primaryClip = cd
}
