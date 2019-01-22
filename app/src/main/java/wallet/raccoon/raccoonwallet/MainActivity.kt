package wallet.raccoon.raccoonwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.view.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun setLayout() = R.layout.activity_main

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSplash()
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

    private fun setupNavigationRecyclerView() {
        val myProfileString =
            SharedPreferenceUtils[this@MainActivity, KEY_PREF_MY_PROFILE, Gson().toJson(MyProfileEntity())]
        val myProfile = Gson().fromJson(myProfileString, MyProfileEntity::class.java)
        controller = DrawerListController(
            this@MainActivity,
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
