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
import wallet.raccoon.raccoonwallet.util.ToastUtil
import wallet.raccoon.raccoonwallet.viewmodel.HomeFragmentViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: HomeFragmentViewModel

  override fun layoutRes() = R.layout.fragment_home

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)

    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(HomeFragmentViewModel::class.java)

  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.harvestInfoData.observe(this, Observer {
      ToastUtil.show(context!!, R.string.app_name)
    })

    CoroutineScope(Dispatchers.IO).launch {
      viewModel.loadHarvestInfo("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
    }
  }

  companion object {
    fun newInstance(): HomeFragment {
      val fragment = HomeFragment()
      return fragment
    }
  }
}