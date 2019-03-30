package wallet.raccoon.raccoonwallet.extentions

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import wallet.raccoon.raccoonwallet.R
import java.util.Calendar
import java.util.TimeZone

@BindingAdapter("loadImg")
fun setImage(
  imageView: ImageView,
  url: String
) {
  if (url.isEmpty()) {
    return
  }
  Picasso
      .with(imageView.context)
      .load(url)
      .placeholder(R.drawable.ic_refresh_black_24dp)
      .error(R.drawable.ic_broken_image_black_24dp)
      .into(imageView)
}

internal fun getNemStartDateTimeLong(): Long {
  val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
  cal.set(2015, 3 - 1, 29, 0, 6, 25)
  return (cal.timeInMillis)
}

fun TextView.buildSpannableText(spans: (SpannableStringBuilder) -> Unit) =
  (text as? SpannableStringBuilder)
      .let { it ?: SpannableStringBuilder(text) }
      .let { text = it.also(spans) }

fun SpannableStringBuilder.setSpan(
  what: Any,
  target: String
): SpannableStringBuilder {
  val startIndex = indexOf(target)
  val endIndex = startIndex + target.length
  setSpan(what, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
  return this
}

fun SpannableStringBuilder.setSpan(
  what: Any,
  target: Regex
): SpannableStringBuilder {
  target.findAll(this)
      .iterator()
      .forEach {
        setSpan(what, it.range.start, it.range.last + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
      }
  return this
}
