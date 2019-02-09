package wallet.raccoon.raccoonwallet.extentions

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
