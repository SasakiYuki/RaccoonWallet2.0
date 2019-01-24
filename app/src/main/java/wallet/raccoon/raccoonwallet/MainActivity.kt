package wallet.raccoon.raccoonwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.view.BaseActivity
import wallet.raccoon.raccoonwallet.view.DrawerListController
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    private lateinit var viewModel: MainActivityViewModel
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun setLayout() = R.layout.activity_main

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSplash()
    }

    private fun setupViweModel() {
        viewModel = ViewModelProviders.of(this)
            .get(MainActivityViewModel::class.java)

        viewModel.myProfileData.observe(this, Observer {

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
        controller = DrawerListController(
            this,
            if (myProfile.name.isEmpty()) getString(R.string.my_address_profile_activity_title_initial_guest) else myProfile.name,
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

        fun createIntent(context: Context, showSplash: Boolean): Intent {
            val intent = createIntent(context)
            intent.putExtra(ARG_SHOULD_SHOW_SPLASH, showSplash)
            return intent
        }
    }
}
