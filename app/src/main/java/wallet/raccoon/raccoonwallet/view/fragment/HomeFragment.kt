package wallet.raccoon.raccoonwallet.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.balanceTextView
import kotlinx.android.synthetic.main.fragment_home.harvestEmptyView
import kotlinx.android.synthetic.main.fragment_home.miniHarvestItemView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import wallet.raccoon.raccoonwallet.viewmodel.HomeFragmentViewModel
import java.text.NumberFormat
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
      it.data.getOrNull(0)
          ?.let { harvestInfo ->
            harvestEmptyView.visibility = View.GONE
            miniHarvestItemView.setupHarvest(harvestInfo)
          }
    })

    viewModel.transactionList.observe(this, Observer {
      
    })

    viewModel.accountInfoData.observe(this, Observer {
      it?.let { accountMetaDataPair ->
        balanceTextView.text = NumberFormat.getNumberInstance()
            .format(accountMetaDataPair.account.balance.convertNEMFromMicroToDouble())
      }
    })

    CoroutineScope(Dispatchers.IO).launch {
      // TODO アドレスをアカウントから取得する
      viewModel.loadHarvestInfo("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
      viewModel.loadAccountInfo("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
      viewModel.loadTransactionList("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
    }
  }

  companion object {
    fun newInstance(): HomeFragment {
      val fragment = HomeFragment()
      return fragment
    }
  }
}