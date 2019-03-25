package wallet.raccoon.raccoonwallet.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.view_raccoon_single_material_button.view.defaultMaterialButton
import kotlinx.android.synthetic.main.view_raccoon_single_material_button.view.imageView
import kotlinx.android.synthetic.main.view_raccoon_single_material_button.view.textView
import wallet.raccoon.raccoonwallet.R

class RaccoonSingleMaterialButton(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
  constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
  constructor(context: Context?) : this(context, null, 0)

  init {
    val view = View.inflate(context, R.layout.view_raccoon_single_material_button, this)
    context?.obtainStyledAttributes(attrs, R.styleable.RaccoonSingleMaterialButton, defStyleAttr, 0)?.let {
      try {
        view.textView.text = it.getString(R.styleable.RaccoonSingleMaterialButton_text)
        val resourceId = it.getResourceId(R.styleable.RaccoonSingleMaterialButton_src, R.mipmap.icon_harvest_small)
        view.imageView.setImageDrawable(ContextCompat.getDrawable(context, resourceId))
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        it.recycle()
      }
    }
  }

  fun setClickListener(listener: OnClickListener) {
    defaultMaterialButton.setOnClickListener { listener.onClick(this) }
  }

  fun setText(textResourceId: Int) {
    textView.text = context.getString(textResourceId)
  }

  fun setImage(imageResourceId: Int) {
    imageView.setImageDrawable(getDrawable(context, imageResourceId))
  }
}
