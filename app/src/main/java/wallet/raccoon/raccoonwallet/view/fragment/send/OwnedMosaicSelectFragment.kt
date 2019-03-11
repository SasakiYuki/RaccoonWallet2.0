package wallet.raccoon.raccoonwallet.view.fragment.send

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_owned_mosaic_select.recycler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.local.FullMosaicItem
import wallet.raccoon.raccoonwallet.util.ToastUtil
import wallet.raccoon.raccoonwallet.view.controller.OwnedMosaicSelectListController
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.OwnedMosaicSelectFragmentViewModel
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class OwnedMosaicSelectFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: OwnedMosaicSelectFragmentViewModel
  private lateinit var controller: OwnedMosaicSelectListController
  private val ownedFullMosaics = ArrayList<FullMosaicItem>()

  override fun layoutRes() = R.layout.fragment_owned_mosaic_select

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    controller = OwnedMosaicSelectListController()
    recycler.layoutManager = LinearLayoutManager(recycler.context)
    recycler.setController(controller)

    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(OwnedMosaicSelectFragmentViewModel::class.java)

    viewModel.ownedMosaicsData.observe(this, Observer {
      ToastUtil.show(context!!, R.string.select_wallet_activity_title)
    })
    viewModel.namespaceData.observe(this, Observer {
      CoroutineScope(Dispatchers.IO).launch {
        for (item in it) {
          viewModel.loadNamespaceMosaic(item)
              .await()
          Single.timer(500L, MILLISECONDS)
              .await()
        }
      }
    })
    viewModel.fullMosaicItemData.observe(this, Observer {
      ownedFullMosaics.add(it)
      controller.setData(ownedFullMosaics)
    })

    CoroutineScope(Dispatchers.IO).launch {
      // TODO AddressをWalletから持ってる
      viewModel.loadOwnedMosaic("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
    }
  }

  companion object {
    fun newInstance(): OwnedMosaicSelectFragment {
      val fragment = OwnedMosaicSelectFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, R.string.enter_send_fragment_title)
      }
      return fragment
    }
  }
}