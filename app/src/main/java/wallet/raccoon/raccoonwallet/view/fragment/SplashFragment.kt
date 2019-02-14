package wallet.raccoon.raccoonwallet.view.fragment

import android.content.Context
import dagger.android.support.AndroidSupportInjection
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import javax.inject.Inject

class SplashFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: HomeFragmentViewModel

    override fun layoutRes() = R.layout.fragment_splash

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

//        viewModel = ViewModelProviders.of(this, viewModelFactory)
//            .get(HomeFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): SplashFragment {
            val fragment = SplashFragment()
            return fragment
        }
    }
}