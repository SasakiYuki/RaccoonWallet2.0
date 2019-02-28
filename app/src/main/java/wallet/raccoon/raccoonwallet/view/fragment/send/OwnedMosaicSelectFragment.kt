package wallet.raccoon.raccoonwallet.view.fragment.send

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.view.controller.OwnedMosaicSelectListController
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.OwnedMosaicSelectFragmentViewModel
import javax.inject.Inject

class OwnedMosaicSelectFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: OwnedMosaicSelectFragmentViewModel
  private lateinit var controller : OwnedMosaicSelectListController

  override fun layoutRes() = R.layout.fragment_owned_mosaic_select

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)

    controller = OwnedMosaicSelectListController()



    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(OwnedMosaicSelectFragmentViewModel::class.java)
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