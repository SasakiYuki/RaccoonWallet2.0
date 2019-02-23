package wallet.raccoon.raccoonwallet.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.R.color
import wallet.raccoon.raccoonwallet.R.id
import wallet.raccoon.raccoonwallet.R.layout
import wallet.raccoon.raccoonwallet.R.string
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.model.DrawerEntity
import wallet.raccoon.raccoonwallet.model.DrawerItemType
import wallet.raccoon.raccoonwallet.model.MainBottomNavigationType
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.util.ToastUtil
import wallet.raccoon.raccoonwallet.view.adapter.TopFragmentPagerAdapter
import wallet.raccoon.raccoonwallet.view.controller.DrawerListController
import wallet.raccoon.raccoonwallet.view.fragment.SplashFragment
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector{

  private lateinit var viewModel: MainActivityViewModel
  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

  override fun setLayout() = layout.activity_main

  override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setupViewModel()
    showSplash()
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

    viewModel.splashCompleteEvent.observe(this, Observer {
      onCompleteSplash()
    })
  }

  private fun showSplash() {
    if (shouldShowSplash) {
      val fragmentTransaction = supportFragmentManager.beginTransaction()
      val fragment = SplashFragment.newInstance()
      fragmentTransaction.replace(id.fragment_container, fragment, fragment::class.java.simpleName)
      fragmentTransaction.commit()
    } else {
      onCompleteSplash()
    }
  }

  private fun onCompleteSplash() {
    setupViewPager()
    setupBottomTabLayout()

    nemIcon.setOnClickListener {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawers()
      else drawerLayout.openDrawer(GravityCompat.START)
    }

    CoroutineScope(Dispatchers.IO).launch {
      viewModel.loadMyProfile()
    }
  }

  private fun setupViewPager() {
    val adapter = TopFragmentPagerAdapter(supportFragmentManager)
    viewpager.adapter = adapter
    tabLayout.setupWithViewPager(viewpager)
    viewpager.currentItem =
        HOME_POSITION
    viewpager.offscreenPageLimit = 5
  }

  private fun setupBottomTabLayout() {
    val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    setupTabAt(inflater, 0)
    setupTabAt(inflater, 1)
    setupTabAt(inflater, 2)
    setupTabAt(inflater, 3)
    setupTabAt(inflater, 4)
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(tab: TabLayout.Tab?) {
      }

      override fun onTabUnselected(tab: TabLayout.Tab?) {
        val color = color.textGray
        tab?.let {
          getTabTextView(tab)?.let {
            setTextColor(it, color)
          }
          getTabImageView(tab)?.let {
            setDrawableTint(it, color)
          }
        }
      }

      override fun onTabSelected(tab: TabLayout.Tab?) {
        val color = color.nemGreen
        tab?.let {
          getTabTextView(tab)?.let {
            setTextColor(it, color)
          }
          getTabImageView(tab)?.let {
            setDrawableTint(it, color)
          }
        }
      }

    })
  }

  private fun setDrawableTint(
    imageView: ImageView,
    color: Int
  ) {
    imageView.setColorFilter(
        ContextCompat.getColor(this@MainActivity, color), android.graphics.PorterDuff.Mode.SRC_IN
    )
  }

  private fun setTextColor(
    textView: TextView,
    color: Int
  ) {
    textView.setTextColor(ContextCompat.getColor(this, color))
  }

  private fun getTabImageView(tab: TabLayout.Tab): ImageView? {
    tab.customView?.let {
      return it.findViewById(id.tabLayoutImageView)
    }
    return null
  }

  private fun getTabTextView(tab: TabLayout.Tab): TextView? {
    tab.customView?.let {
      return it.findViewById(id.tabLayoutTextView)
    }
    return null
  }

  private fun setupTabAt(
    layoutInflater: LayoutInflater,
    position: Int
  ) {
    val tab = tabLayout.getTabAt(position)
    val items = MainBottomNavigationType.values()
    val text = getString(items[position].textResource)

    val tab1View = layoutInflater.inflate(layout.view_main_tab, null)
    val textView = tab1View.findViewById<TextView>(
        id.tabLayoutTextView
    )
    val imageView = tab1View.findViewById<ImageView>(
        id.tabLayoutImageView
    )
    textView.text = text
    if (position == HOME_POSITION) {
      val color = color.nemGreen
      setTextColor(textView, color)
      setDrawableTint(imageView, color)
    }
    imageView.setImageDrawable(ContextCompat.getDrawable(this, items[position].drawableResource))
    tab!!.customView = tab1View
  }

  private fun setupNavigationRecyclerView(myProfile: MyProfileEntity) {
    val controller = DrawerListController(
        this,
        if (myProfile.name.isEmpty()) getString(
            string.my_address_profile_activity_title_initial_guest
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
    intent.getBooleanExtra(
        ARG_SHOULD_SHOW_SPLASH, true)
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
      val intent =
        createIntent(context)
      intent.putExtra(
          ARG_SHOULD_SHOW_SPLASH, showSplash)
      return intent
    }
  }
}
