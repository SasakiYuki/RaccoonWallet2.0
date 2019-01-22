package wallet.raccoon.raccoonwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import wallet.raccoon.raccoonwallet.view.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun setLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val shouldShowSplash by lazy {
        intent.getBooleanExtra(ARG_SHOULD_SHOW_SPLASH, true)
    }

    companion object {
        const val SP_IS_FIRST_RACCOON = "sp_is_first_raccoon"
        private const val HOME_POSITION = 2
        private const val ARG_SHOULD_SHOW_SPLASH = "args_show_splash"

        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)

        fun createIntent(context: Context, showSplash: Boolean): Intent {
            val intent = createIntent(context)
            intent.putExtra(ARG_SHOULD_SHOW_SPLASH, showSplash)
            return intent
        }
    }
}
