package wallet.raccoon.raccoonwallet.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import wallet.raccoon.raccoonwallet.viewmodel.SplashFragmentViewModel
import javax.inject.Inject

class SplashFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SplashFragmentViewModel

    override fun layoutRes() = R.layout.fragment_splash

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SplashFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.activeNodeData.observe(this, Observer { activeNode ->
            SharedPreferenceUtils(context!!).activeNode = activeNode

        })

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadActiveNode()
        }
    }

    companion object {
        fun newInstance(): SplashFragment {
            val fragment = SplashFragment()
            return fragment
        }
    }
}