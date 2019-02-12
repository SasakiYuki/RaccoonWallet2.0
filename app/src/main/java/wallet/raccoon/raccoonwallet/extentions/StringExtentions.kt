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

fun String.remove(target: String): String {
  val charArray = this.toCharArray()
  val stringBuilder = StringBuilder()
  charArray
      .filter { it != target.single() }
      .forEach { stringBuilder.append(it) }
  return String(stringBuilder)
}
