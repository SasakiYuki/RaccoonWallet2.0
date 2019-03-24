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
import wallet.raccoon.raccoonwallet.view.activity.SendActivity
import wallet.raccoon.raccoonwallet.view.controller.OwnedMosaicSelectListController
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.OwnedMosaicSelectFragmentViewModel
import wallet.raccoon.raccoonwallet.viewmodel.send.SendActivityViewModel
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class OwnedMosaicSelectFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var ownedMosaicSelectFragmentViewModel: OwnedMosaicSelectFragmentViewModel
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

    controller = OwnedMosaicSelectListController(activity as SendActivity, false, true)
    recycler.layoutManager = LinearLayoutManager(recycler.context)
    recycler.setController(controller)

    ownedMosaicSelectFragmentViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(OwnedMosaicSelectFragmentViewModel::class.java)

    ownedMosaicSelectFragmentViewModel.ownedMosaicsData.observe(this, Observer {
    })
    ownedMosaicSelectFragmentViewModel.namespaceData.observe(this, Observer {
      CoroutineScope(Dispatchers.IO).launch {
        for (item in it) {
          ownedMosaicSelectFragmentViewModel.loadNamespaceMosaic(item)
              .await()
          Single.timer(500L, MILLISECONDS)
              .await()
        }
      }
    })
    ownedMosaicSelectFragmentViewModel.fullMosaicItemData.observe(this, Observer {
      ownedFullMosaics.add(it)
      controller.setData(ownedFullMosaics)
    })

    CoroutineScope(Dispatchers.IO).launch {
      // TODO AddressをWalletから持ってる
      ownedMosaicSelectFragmentViewModel.loadOwnedMosaic("NCMKWNFWUILEVCKBSON2MS65BXU4NJ2GBJTIJBTK")
    }

    activity?.let { fragmentActivity ->
      ViewModelProviders.of(fragmentActivity)
          .get(SendActivityViewModel::class.java)
          .let { viewModel ->
            viewModel.automaticAddedXEMData.observe(this, Observer {
              controller.showHeader = false
              controller.setData(ownedFullMosaics)
            })
            viewModel.addedMosaicData.observe(this, Observer {
              controller.switchState = true
              controller.showHeader = true
              controller.setData(ownedFullMosaics)
            })
            viewModel.mosaicSelectedData.observe(this, Observer {
              val list = ArrayList<FullMosaicItem>()
              for (item in ownedFullMosaics) {
                if (item.getFullName() == it.getFullName()) {
                  list.add(item.changeSelectedState())
                } else {
                  list.add(item)
                }
              }
              ownedFullMosaics.clear()
              ownedFullMosaics.addAll(list)
            })
          }
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