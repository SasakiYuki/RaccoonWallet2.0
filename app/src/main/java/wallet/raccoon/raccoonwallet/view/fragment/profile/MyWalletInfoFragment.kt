package wallet.raccoon.raccoonwallet.view.fragment.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_my_wallet_info.recyclerView
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.extentions.copyClipBoard
import wallet.raccoon.raccoonwallet.extentions.showToast
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.type.ProfileAddressAddType
import wallet.raccoon.raccoonwallet.type.event.WalletInfoEvent
import wallet.raccoon.raccoonwallet.view.activity.profile.MyAddressProfileActivity
import wallet.raccoon.raccoonwallet.view.activity.profile.ProfileAddressAddActivity
import wallet.raccoon.raccoonwallet.view.controller.WalletInfoClickListener
import wallet.raccoon.raccoonwallet.view.controller.WalletInfoListController
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.view.fragment.BottomSheetListDialogFragment
import wallet.raccoon.raccoonwallet.viewmodel.MyWalletInfoViewModel
import wallet.raccoon.raccoonwallet.viewmodel.profile.MyAddressProfileViewModel
import javax.inject.Inject

class MyWalletInfoFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var myWalletInfoViewModel: MyWalletInfoViewModel
  private lateinit var controller: WalletInfoListController
  override fun layoutRes() = R.layout.fragment_my_wallet_info

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)

    myWalletInfoViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(MyWalletInfoViewModel::class.java)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
    setupViewModel()


    if (activity != null && activity is MyAddressProfileActivity) {
      ViewModelProviders.of(activity!!, viewModelFactory)
          .get(MyAddressProfileViewModel::class.java)
          .let { viewModel ->
            viewModel.walletInfoEvent.observe(this, Observer {
              if (it is WalletInfoEvent.InsertWalletInfo) {
                myWalletInfoViewModel.walletInfoItems.add(it.walletInfo)
              } else if (it is WalletInfoEvent.UpdateWalletInfo) {
                val list = Observable.fromIterable(myWalletInfoViewModel.walletInfoItems)
                    .filter { walletInfo ->
                      it.walletInfo.id != walletInfo.id
                    }
                    .toList()
                    .blockingGet() as ArrayList<WalletInfo>
                list.add(it.walletInfo)
                myWalletInfoViewModel.walletInfoItems.clear()
                myWalletInfoViewModel.walletInfoItems.addAll(list)
              }
            })
          }
    }

    myWalletInfoViewModel.findAllMyAddress()
  }

  private fun setupViewModel() {
    myWalletInfoViewModel.apply {
      myAddressLiveData.observe(this@MyWalletInfoFragment, Observer {
        it ?: return@Observer
        myWalletInfoViewModel.selectWalletInfo(it.walletInfoId)
      })
      walletInfoUpdatedLiveData.observe(this@MyWalletInfoFragment, Observer {
        it ?: return@Observer
        controller.setData(myWalletInfoViewModel.walletInfoItems)
        myWalletInfoViewModel.walletInfoItems
            .firstOrNull { it.isMaster }
            ?.let {
              myWalletInfoViewModel.sendBusMasterWallet(it)
            }
      })
    }
  }

  private fun setupViews() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    controller = WalletInfoListController(object : WalletInfoClickListener {
      override fun onRowClick(walletInfo: WalletInfo) {
        val fragment = BottomSheetListDialogFragment.newInstance(
            getString(R.string.bottom_my_wallet_info_copy), R.menu.bottom_my_wallet_info
        ) { fragment, itemId ->
          when (itemId) {
            R.id.copy -> onClickCopyRow(walletInfo)
            R.id.send -> onClickSendRow(walletInfo)
            R.id.edit -> onClickEditRow(walletInfo)
            R.id.delete -> onClickDeleteRow(walletInfo)
          }
          fragment.dismiss()
        }
        fragment.show(activity?.supportFragmentManager, fragment.tag)
      }
    })
    controller.setData(null)
    recyclerView.adapter = controller.adapter
  }

  private fun onClickCopyRow(walletInfo: WalletInfo) {
    context?.let {
      it.showToast(R.string.my_wallet_info_fragment_address_copied_toast)
      walletInfo.walletAddress.copyClipBoard(it)
    }
  }

  private fun onClickSendRow(walletInfo: WalletInfo) {
    activity?.let {
      it.setResult(
          Activity.RESULT_OK, Intent().putExtra(
          MyAddressProfileActivity.RESULT_PAYMENT_ADDRESS,
          walletInfo.walletAddress
      )
      )
      finish()
    }
  }

  private fun onClickEditRow(walletInfo: WalletInfo) {
    activity?.let {
      startActivity(
          ProfileAddressAddActivity.createIntent(it,
          ProfileAddressAddType.Edit,
          walletInfo))
    }
  }

  private fun onClickDeleteRow(walletInfo: WalletInfo) {
    myWalletInfoViewModel.deleteMyAddress(walletInfo)
  }

  companion object {
    fun newInstance(): MyWalletInfoFragment {
      return MyWalletInfoFragment().apply {
        val args = Bundle()
        args.putInt(ARG_CONTENTS_NAME_ID, R.string.my_address_profile_activity_tab_wallet)
        arguments = args
      }
    }
  }
}
