package wallet.raccoon.raccoonwallet.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import wallet.raccoon.raccoonwallet.R
import java.util.Calendar
import java.util.TimeZone

@BindingAdapter("loadImg")
fun setImage(imageView: ImageView, url: String) {
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
