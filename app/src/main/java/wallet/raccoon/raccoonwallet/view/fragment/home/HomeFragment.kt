package wallet.raccoon.raccoonwallet.view.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import wallet.raccoon.raccoonwallet.extentions.remove
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
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
    setupViewModel()

    CoroutineScope(Dispatchers.IO).launch {
      //todo 現在選択されているWalletIdをSharedPreferenceから取得する
      viewModel.loadWallet(0)
    }
  }

  private fun setupViewModel(){
    viewModel.harvestInfoData.observe(this, Observer {
      it.data.getOrNull(0)
        ?.let { harvestInfo ->
          harvestEmptyView.visibility = View.GONE
          miniHarvestItemView.setupHarvest(harvestInfo)
        }
    })

    viewModel.transactionList.observe(this, Observer {
      setupTransactionItems(it.data)
    })

    viewModel.accountInfoData.observe(this, Observer {
      it?.let { accountMetaDataPair ->
        balanceTextView.text = NumberFormat.getNumberInstance()
          .format(accountMetaDataPair.account.balance.convertNEMFromMicroToDouble())
      }
      CoroutineScope(Dispatchers.IO).launch {
        viewModel.loadNemPrice()
      }
    })

    viewModel.nemPriceData.observe(this, Observer {
      val balanceText = balanceTextView.text.toString()
        .remove(",")
        .toDouble()
      nemJpPriceTextView.text = getString(
        R.string.home_fragment_jpy_before,
        NumberFormat.getNumberInstance().format(balanceText * it.last_price)
      )
    })

    viewModel.walletData.observe(this, Observer {
      CoroutineScope(Dispatchers.IO).launch {
        // TODO アドレスをアカウントから取得する
//        val address = it.address
        val address = "NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK"
        viewModel.loadHarvestInfo(address)
        viewModel.loadAccountInfo(address)
        viewModel.loadTransactionList(address)
      }
    })
  }

  private fun setupTransactionItems(list: List<TransactionMetaDataPair>) {
    val address = "NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK" // TODO アドレスをアカウントから取得する
    val selectedList = ArrayList<TransactionMetaDataPair>()
    list.filterTo(
        selectedList
    ) { it.transaction.mosaics == null || (it.transaction.mosaics != null && it.transaction.mosaics!!.isEmpty()) }
    if (selectedList.isNotEmpty()) {
      transactionEmptyView.visibility = View.GONE
      miniTransactionItemView1.setupTransaction(address, list[0])
      if (selectedList.size > 1) {
        miniTransactionItemView2.setupTransaction(address, list[1])
      } else {
        miniTransactionItemView2.visibility = View.GONE
      }

      if (selectedList.size > 2) {
        miniTransactionItemView3.setupTransaction(address, list[2])
      } else {
        miniTransactionItemView3.visibility = View.GONE
      }

      if (selectedList.size > 3) {
        miniTransactionItemView4.setupTransaction(address, list[3])
      } else {
        miniTransactionItemView4.visibility = View.GONE
      }
    }
  }

  companion object {
    fun newInstance(): HomeFragment {
      val fragment = HomeFragment()
      return fragment
    }
  }
}