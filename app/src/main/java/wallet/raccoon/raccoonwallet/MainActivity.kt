package wallet.raccoon.raccoonwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.drawerLayout
import kotlinx.android.synthetic.main.activity_main.navigationRecyclerView
import kotlinx.android.synthetic.main.activity_main.nemIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.DrawerEntity
import wallet.raccoon.raccoonwallet.model.DrawerItemType
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.util.ToastUtil
import wallet.raccoon.raccoonwallet.view.BaseActivity
import wallet.raccoon.raccoonwallet.view.DrawerListController
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(),
    HasSupportFragmentInjector {

  private lateinit var viewModel: MainActivityViewModel
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

  override fun setLayout() = R.layout.activity_main

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setupViewModel()
    showSplash()

    nemIcon.setOnClickListener {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawers()
      else drawerLayout.openDrawer(GravityCompat.START)
    }

    CoroutineScope(Dispatchers.IO).launch {
      viewModel.loadMyProfile()
    }
  }

  private fun setupViewModel() {
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(MainActivityViewModel::class.java)

    viewModel.myProfileData.observe(this, Observer {
      setupNavigationRecyclerView(it)
    })

    viewModel.navigationClickEvent.observe(this, Observer {
      // TODO 遷移をそれぞれ実装　ドロワー
      ToastUtil.show(this, R.string.app_name)
    })
  }

  private fun showSplash() {
    if (shouldShowSplash) {
      // TODO SplashFragmentの実装
//            val fragmentTransaction = supportFragmentManager.beginTransaction()
//            val fragment = SplashFragment.newInstance()
//            fragmentTransaction.replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
//            fragmentTransaction.commit()
    } else {
//            hideSplash()
    }
  }

  private fun setupNavigationRecyclerView(myProfile: MyProfileEntity) {
    val controller = DrawerListController(
        this,
        if (myProfile.name.isEmpty()) getString(
            R.string.my_address_profile_activity_title_initial_guest
        ) else myProfile.name,
        myProfile.screenPath,
        myProfile.iconPath
    )
    navigationRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    navigationRecyclerView.adapter = controller.adapter
    val drawerIconTypes = DrawerItemType.values()
    val list = ArrayList<DrawerEntity>()
    drawerIconTypes.mapTo(list) {
      DrawerEntity(
          ContextCompat.getDrawable(this@MainActivity, it.imageResource)!!,
          getString(it.titleResource),
          it.drawerType
      )
    }
    controller.setData(list)
  }

  private val shouldShowSplash by lazy {
    intent.getBooleanExtra(ARG_SHOULD_SHOW_SPLASH, true)
  }

  companion object {
    const val SP_IS_FIRST_RACCOON = "sp_is_first_raccoon"
    private const val HOME_POSITION = 2
    private const val ARG_SHOULD_SHOW_SPLASH = "args_show_splash"

    fun createIntent(context: Context) = Intent(context, MainActivity::class.java)

    fun createIntent(
      context: Context,
      showSplash: Boolean
    ): Intent {
      val intent = createIntent(context)
      intent.putExtra(ARG_SHOULD_SHOW_SPLASH, showSplash)
      return intent
    }
  }
}
