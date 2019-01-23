package wallet.raccoon.raccoonwallet.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import wallet.raccoon.raccoonwallet.R

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
