package wallet.raccoon.raccoonwallet.view.activity.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_my_address_profile.bottomButton
import kotlinx.android.synthetic.main.activity_my_address_profile.tabs
import kotlinx.android.synthetic.main.activity_my_address_profile.toolbar
import kotlinx.android.synthetic.main.activity_my_address_profile.toolbarTitle
import kotlinx.android.synthetic.main.activity_my_address_profile.viewpager
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.R.color
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.extentions.buildSpannableText
import wallet.raccoon.raccoonwallet.extentions.setSpan
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.type.MyAddressBottomButtonType.CHANGE
import wallet.raccoon.raccoonwallet.type.MyAddressBottomButtonType.COMPLETE
import wallet.raccoon.raccoonwallet.type.MyAddressBottomButtonType.EDIT
import wallet.raccoon.raccoonwallet.type.event.WalletInfoEvent
import wallet.raccoon.raccoonwallet.view.activity.BaseActivity
import wallet.raccoon.raccoonwallet.view.adapter.SimpleViewPagerAdapter
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.view.fragment.profile.MyProfileInfoFragment
import wallet.raccoon.raccoonwallet.view.fragment.profile.MyWalletInfoFragment
import wallet.raccoon.raccoonwallet.viewmodel.profile.MyAddressProfileViewModel
import javax.inject.Inject

class MyAddressProfileActivity : BaseActivity(), HasSupportFragmentInjector {
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: MyAddressProfileViewModel
  override fun setLayout() = R.layout.activity_my_address_profile

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setupViewModel()
  }

  override fun onResume() {
    super.onResume()
    setupViews()
  }

  private fun setupViewModel() {
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(MyAddressProfileViewModel::class.java)
    viewModel.apply {
      insertMyAddressLiveData.observe(this@MyAddressProfileActivity, Observer {
        it ?: return@Observer
        // do nothing
      })
      insertWalletInfoLiveData.observe(this@MyAddressProfileActivity, Observer {
        it ?: return@Observer
        MyAddress(walletInfoId = it.id).let {
          viewModel.insertMyAddress(it)
        }
        viewModel.walletInfoEvent.postValue(WalletInfoEvent.InsertWalletInfo(it))
      })
      myProfileEntityEvent.observe(this@MyAddressProfileActivity, Observer {
        it ?: return@Observer
        setupToolbarTitle(it)
      })
    }
  }

  private fun setupToolbarTitle(myProfileEntity: MyProfileEntity) {
    if (myProfileEntity.name.isNotEmpty()) {
      toolbarTitle.apply {
        text = (myProfileEntity.name + "\n" + myProfileEntity.nameRuby)
      }
          .buildSpannableText {
            val targetTop = myProfileEntity.name
            val targetBottom = myProfileEntity.nameRuby
            it.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MyAddressProfileActivity, color.textBlack)
                ), targetTop
            )
                .setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(this@MyAddressProfileActivity, R.color.textGrayDark)
                    ), targetBottom
                )
                .setSpan(AbsoluteSizeSpan(20, true), targetTop)
                .setSpan(AbsoluteSizeSpan(14, true), targetBottom)
          }
    }
  }

  private fun setupViews() {
    setupToolbar()
    setupViewPager()
    changeAddBottomButton()
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
      title = ""
      toolbarTitle.apply {
        text = getString(R.string.my_address_profile_activity_title_initial)
      }
          .buildSpannableText {
            val targetTop = getString(R.string.my_address_profile_activity_title_initial_guest)
            val targetBottom =
              getString(R.string.my_address_profile_activity_title_initial_bottom_guest)
            it.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MyAddressProfileActivity, R.color.textBlack)
                ), targetTop
            )
                .setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(this@MyAddressProfileActivity, R.color.textGrayDark)
                    ), targetBottom
                )
                .setSpan(AbsoluteSizeSpan(20, true), targetTop)
                .setSpan(AbsoluteSizeSpan(14, true), targetBottom)
          }
      setDisplayHomeAsUpEnabled(true)
    }
  }

  private fun setupViewPager() {
    ArrayList<BaseFragment>().let {
      it.add(MyWalletInfoFragment.newInstance())
      it.add(MyProfileInfoFragment.newInstance())
      SimpleViewPagerAdapter(this, it, supportFragmentManager).let {
        viewpager.adapter = it
      }
    }
    tabs.setupWithViewPager(viewpager)
    viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {
        // do nothing
      }

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
        // do nothing
      }

      override fun onPageSelected(position: Int) {
        when (position) {
          0 -> changeAddBottomButton()
          1 -> changeEditBottomButton()
        }
      }
    })
  }

  private fun changeAddBottomButton() {
    bottomButton.setText(R.string.my_address_profile_activity_bottom_button_add)
    bottomButton.setImage(R.mipmap.icon_plus)
    bottomButton.setClickListener(View.OnClickListener {
      startActivityForResult(
          SelectModeAddWalletActivity.createIntent(this), SelectModeAddWalletActivity.REQUEST_CODE
      )
    })
    viewModel.bottomButtonEvent.postValue(CHANGE)
  }

  private fun changeEditBottomButton() {
    bottomButton.setText(R.string.my_address_profile_activity_bottom_button_edit)
    bottomButton.setImage(R.mipmap.icon_pencil)
    bottomButton.setClickListener(View.OnClickListener {
      viewModel.bottomButtonEvent.postValue(EDIT)
      changeCompleteBottomButton()
    })
  }

  private fun changeCompleteBottomButton() {
    bottomButton.setText(R.string.my_address_profile_activity_bottom_button_complete)
    bottomButton.setImage(R.mipmap.icon_check_gray2)
    bottomButton.setClickListener(View.OnClickListener {
      viewModel.bottomButtonEvent.postValue(COMPLETE)
      changeEditBottomButton()
    })
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    data?.let {
      when (requestCode) {
        ProfileAddressAddActivity.REQUEST_CODE -> handleProfileAddressAddActivity(resultCode, it)
//        SelectMyProfileAddressAddActivity.REQUEST_CODE -> handleSelectMyProfileAddressAddActivity(
//            resultCode, it
//        )
        SelectModeAddWalletActivity.REQUEST_CODE -> handleSelectModeAddWalletActivity(
            resultCode, it
        )
      }
    }
  }

  private fun handleProfileAddressAddActivity(
    resultCode: Int,
    intent: Intent
  ) {
    if (resultCode == Activity.RESULT_OK) {
      val item =
        intent.getSerializableExtra(ProfileAddressAddActivity.INTENT_WALLET_INFO) as WalletInfo
      MyAddress(walletInfoId = item.id).let {
        viewModel.insertMyAddress(it)
      }
    }
  }

  private fun handleSelectMyProfileAddressAddActivity(
    resultCode: Int,
    intent: Intent
  ) {
//    if (resultCode == Activity.RESULT_OK) {
//      val list = intent.getSerializableExtra(
//          SelectMyProfileAddressAddActivity.KEY_WALLET_ADD_ENTITIES
//      ) as ArrayList<WalletAddEntity>
//      for (item in list) {
//        if (item.isSelected) {
//          viewModel.insertWalletInfo(
//              WalletInfo(
//                  walletName = item.walletName,
//                  walletAddress = item.walletAddress
//              )
//          )
//        }
//      }
//    }
  }

  private fun handleSelectModeAddWalletActivity(
    resultCode: Int,
    intent: Intent
  ) {
    if (resultCode == Activity.RESULT_OK) {
      val item = intent.getSerializableExtra(
          SelectModeAddWalletActivity.KEY_MODE
      ) as SelectModeAddWalletActivity.Mode
      if (item == SelectModeAddWalletActivity.Mode.Wallet) {
//        startActivityForResult(
//            SelectMyProfileAddressAddActivity.createIntent(this@MyAddressProfileActivity),
//            SelectMyProfileAddressAddActivity.REQUEST_CODE
//        )
      } else if (item == SelectModeAddWalletActivity.Mode.Direct) {
        startActivityForResult(
            ProfileAddressAddActivity.createIntent(this@MyAddressProfileActivity),
            ProfileAddressAddActivity.REQUEST_CODE
        )
      }
    }
  }

  companion object {
    const val REQUEST_CODE_MY_ADDRESS_PROFILE_ACTIVITY = 3104
    const val RESULT_PAYMENT_ADDRESS = "result_payment_address"
    fun createIntent(context: Context): Intent {
      val intent = Intent(context, MyAddressProfileActivity::class.java)
      return intent
    }
  }
}
