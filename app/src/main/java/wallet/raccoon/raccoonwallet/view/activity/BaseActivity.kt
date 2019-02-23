package wallet.raccoon.raccoonwallet.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.dialog.LoadingDialogFragment
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

abstract class BaseActivity : AppCompatActivity() {
  private var toolbar: Toolbar? = null
  private var loadingDialog: LoadingDialogFragment? = null

  abstract fun setLayout(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(setLayout())

    setupViews()
  }

  override fun onResume() {
    super.onResume()
    checkClearCache()
  }

  private fun setupViews() {
    toolbar = findViewById(R.id.toolbar)
  }

  protected fun setToolbarTitle(@StringRes titleRes: Int) {
    if (titleRes == BaseFragment.DEFAULT_VALUE_VISIBLE_TOOLBAR) {
      toolbar?.visibility = View.GONE
    } else {
      toolbar?.let {
        it.visibility = View.VISIBLE
        it.title = getString(titleRes)
      }
    }
  }

  protected fun setToolBarBackButton() {
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  protected fun showProgress() {
    val isHidden = loadingDialog?.isHidden.let { it } ?: true

    if (isHidden) {
      loadingDialog = LoadingDialogFragment()
      loadingDialog!!.show(supportFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }
  }

  protected fun hideProgress() {
    val isHidden = loadingDialog?.isHidden.let { it } ?: true

    if (!isHidden) {
      loadingDialog!!.dismiss()
      loadingDialog = null
    }
  }

  private fun checkClearCache() {
    // TODO 有効化
    when {
//      this is MainActivity || this is ChooseCreateOrScanWalletActivity || this is SelectWalletActivity -> clearCache()
    }
  }

  private fun clearCache() {
    // TODO 有効化
//    PinCodeProvider.clearCache()
//    WalletProvider.clearCache()
  }
}
