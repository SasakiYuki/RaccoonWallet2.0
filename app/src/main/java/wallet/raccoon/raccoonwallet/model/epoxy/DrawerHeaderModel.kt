package wallet.raccoon.raccoonwallet.model.epoxy

import wallet.raccoon.raccoonwallet.BR
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import wallet.raccoon.raccoonwallet.R

@EpoxyModelClass(layout = R.layout.row_header_drawer_list)
abstract class DrawerHeaderModel : DataBindingEpoxyModel() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var name: String = ""

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var screenPath: String = ""

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var iconPath: String = ""

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickHeaderListener: View.OnClickListener = View.OnClickListener { }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.let {
            it.setVariable(BR.name, name)
            it.setVariable(BR.clickListener, onClickHeaderListener)

            it.root.apply {
                val userScreenImageView = findViewById<ImageView>(R.id.userScreenImageView)
                if (screenPath.isNotEmpty()) {
                    Picasso.with(context).load(screenPath).error(context.getDrawable(R.mipmap.image_menu_default))
                        .into(userScreenImageView)
                } else {
                    userScreenImageView.setImageDrawable(context.getDrawable(R.mipmap.image_menu_default))
                }
                val circleImageView = findViewById<CircleImageView>(R.id.iconImageView)
                if (iconPath.isNotEmpty()) {
                    Picasso.with(context).load(iconPath).error(context.getDrawable(R.mipmap.logo_raccoon_color))
                        .into(circleImageView)
                } else {
                    circleImageView.setImageDrawable(context.getDrawable(R.mipmap.logo_raccoon_color))
                }
                // TODO プロフィールが更新されたときに
//                RxBus.receive(MyProfileEvent::class.java)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        if (it.myProfileEntity.screenPath.isNotEmpty()) {
//                            Picasso.with(context).load(it.myProfileEntity.screenPath)
//                                .error(context.getDrawable(R.mipmap.image_menu_default)).into(userScreenImageView)
//                        } else {
//                            userScreenImageView.setImageDrawable(context.getDrawable(R.mipmap.image_menu_default))
//                        }
//                        if (it.myProfileEntity.iconPath.isNotEmpty()) {
//                            Picasso.with(context).load(it.myProfileEntity.iconPath)
//                                .error(context.getDrawable(R.mipmap.logo_pyoko)).into(circleImageView)
//                        } else {
//                            circleImageView.setImageDrawable(context.getDrawable(R.mipmap.logo_pyoko))
//                        }
//                        val nameTextView = findViewById<TextView>(R.id.nameTextView)
//                        nameTextView.text = it.myProfileEntity.name
//                    })
            }
        }
    }
}

