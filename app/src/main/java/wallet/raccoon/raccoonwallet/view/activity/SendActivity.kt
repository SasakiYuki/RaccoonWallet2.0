package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_send.amountInputFragmentContainer
import kotlinx.android.synthetic.main.activity_send.ownedMosaicSelectFragment
import kotlinx.android.synthetic.main.activity_send.ownedMosaicSelectFragmentContainer
import kotlinx.android.synthetic.main.activity_send.tabLayout
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.view.fragment.send.AmountInputFragment
import wallet.raccoon.raccoonwallet.view.fragment.send.OwnedMosaicSelectFragment
import wallet.raccoon.raccoonwallet.viewmodel.send.SendActivityViewModel
import javax.inject.Inject

class SendActivity : BaseActivity(), HasSupportFragmentInjector {
  private lateinit var viewModel: SendActivityViewModel
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun setLayout() = R.layout.activity_send

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(SendActivityViewModel::class.java)
    super.onCreate(savedInstanceState)

    viewModel.mosaicSelectedData.observe(this, Observer {selectedFullMosaicItem ->
    })

    setToolBarBackButton()

    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(p0: Tab?) {
      }

      override fun onTabUnselected(p0: Tab?) {
      }

      override fun onTabSelected(p0: Tab?) {
        p0?.let { tab ->
          when (tab.position) {
            0 -> {
              amountInputFragmentContainer.visibility = View.VISIBLE
              ownedMosaicSelectFragmentContainer.visibility = View.GONE
            }
            1 -> {
              amountInputFragmentContainer.visibility = View.GONE
              ownedMosaicSelectFragmentContainer.visibility = View.VISIBLE
            }
          }
        }
      }
    })
  }

  companion object {
    fun createIntent(context: Context): Intent {
      val intent = Intent(context, SendActivity::class.java)
      return intent
    }
  }
}
