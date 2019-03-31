package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.tutorial.TutorialWalletCreateFragment
import javax.inject.Inject

class WalletCreateActivity : BaseFragmentActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun initialFragment() = TutorialWalletCreateFragment.newInstance()

    override fun setLayout() = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setToolBarBackButton()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, WalletCreateActivity::class.java)
        }
    }
}
