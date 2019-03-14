package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_send.tabLayout
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.send.AmountInputFragment
import wallet.raccoon.raccoonwallet.view.fragment.send.OwnedMosaicSelectFragment
import javax.inject.Inject

class SendActivity : BaseFragmentActivity(), HasSupportFragmentInjector {
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun setLayout() = R.layout.activity_send
  override fun initialFragment() = AmountInputFragment.newInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setToolBarBackButton()

    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(p0: Tab?) {
      }

      override fun onTabUnselected(p0: Tab?) {
      }

      override fun onTabSelected(p0: Tab?) {
        p0?.let { tab ->
          when (tab.position) {
            0 ->
              replaceFragment(AmountInputFragment.newInstance(), true)
            1 ->
              replaceFragment(OwnedMosaicSelectFragment.newInstance(), true)
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
