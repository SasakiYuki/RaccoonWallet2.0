package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import wallet.raccoon.raccoonwallet.view.fragment.send.OwnedMosaicSelectFragment
import javax.inject.Inject

class SendActivity : BaseFragmentActivity(), HasSupportFragmentInjector {
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun setLayout() = SIMPLE_FRAGMENT_ONLY_LAYOUT
  override fun initialFragment() = OwnedMosaicSelectFragment.newInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  companion object {
    fun createIntent(context: Context): Intent {
      val intent = Intent(context, SendActivity::class.java)
      return intent
    }
  }
}
