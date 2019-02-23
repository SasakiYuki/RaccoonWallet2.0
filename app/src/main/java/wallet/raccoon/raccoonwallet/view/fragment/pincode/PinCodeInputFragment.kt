package wallet.raccoon.raccoonwallet.view.fragment.pincode

import android.os.Bundle
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_pin_code_input.backButton
import kotlinx.android.synthetic.main.fragment_pin_code_input.button0
import kotlinx.android.synthetic.main.fragment_pin_code_input.button1
import kotlinx.android.synthetic.main.fragment_pin_code_input.button2
import kotlinx.android.synthetic.main.fragment_pin_code_input.button3
import kotlinx.android.synthetic.main.fragment_pin_code_input.button4
import kotlinx.android.synthetic.main.fragment_pin_code_input.button5
import kotlinx.android.synthetic.main.fragment_pin_code_input.button6
import kotlinx.android.synthetic.main.fragment_pin_code_input.button7
import kotlinx.android.synthetic.main.fragment_pin_code_input.button8
import kotlinx.android.synthetic.main.fragment_pin_code_input.button9
import kotlinx.android.synthetic.main.fragment_pin_code_input.displayText
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.extentions.showToast
import wallet.raccoon.raccoonwallet.view.activity.BaseFragmentActivity
import wallet.raccoon.raccoonwallet.view.dialog.RaccoonConfirmDialog
import wallet.raccoon.raccoonwallet.view.dialog.RaccoonConfirmViewModel
import wallet.raccoon.raccoonwallet.view.dialog.RaccoonPagerDialog
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.view.fragment.SimpleMessageFragment
import wallet.raccoon.raccoonwallet.viewmodel.RaccoonPagerViewModel

class PinCodeInputFragment : BaseFragment() {

  private var stringBuilder = StringBuilder()

  private val circleList = intArrayOf(
      R.id.inputView1,
      R.id.inputView2,
      R.id.inputView3,
      R.id.inputView4,
      R.id.inputView5,
      R.id.inputView6
  )

  private val isConfirmMode by lazy {
    arguments?.getBoolean(ARG_IS_CONFIRM_MODE) ?: false
  }

  override fun layoutRes() = R.layout.fragment_pin_code_input

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setupButtons()
    if (isConfirmMode) {
      displayText.text = getString(R.string.pin_code_fragment_confirm)
    } else {
      showPinCodeSettingDialog()
    }
  }

  override fun onResume() {
    super.onResume()
    resetViews()
  }

  private fun resetViews() {
    stringBuilder = StringBuilder()
    for (item in circleList) {
      context?.let {
        view?.findViewById<View>(item)
            ?.background = it.getDrawable(R.drawable.frame_round_transparent)
      }
    }
  }

  private fun addPinCode(value: Int) {
    stringBuilder.append(value)
    changeCircleState()

    if (stringBuilder.length == PIN_MAX_LENGTH) {
      if (isConfirmMode) {
        if (arguments?.getString(ARG_PIN_CODE) == String(stringBuilder)) {
          // rewriteSecretKey()
          // TODO NewPincodeSettingFragment.kt#130 の実装をする
          showSuccessDialog()
        } else {
          context?.showToast(R.string.pin_code_setting_confirm_error)
          resetViews()
        }
      } else {
        replaceFragment(
            PinCodeInputFragment.newInstance(true, String(stringBuilder)),
            true
        )
      }
    }
  }

  private fun showPinCodeSettingDialog() {
    val list = ArrayList<SimpleMessageFragment>()
    val fragment1 =
      SimpleMessageFragment.newInstance(getString(R.string.pin_code_setting_fragment_dialog_1))
    val fragment2 =
      SimpleMessageFragment.newInstance(getString(R.string.pin_code_setting_fragment_dialog_2))
    list.add(fragment1)
    list.add(fragment2)
    RaccoonPagerDialog.createDialog(
        RaccoonPagerViewModel(), getString(R.string.pin_code_setting_fragment_dialog_title),
        getString(R.string.com_ok), list
    )
        .show(activity?.supportFragmentManager, RaccoonPagerDialog::class.java.toString())
  }

  private fun showSuccessDialog() {
    val viewModel = RaccoonConfirmViewModel()

    viewModel.closeEvent
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          replaceFragment(CompletedPinCodeSettingFragment.newInstance(), true)
        }

    RaccoonConfirmDialog.createDialog(
        viewModel, getString(R.string.pin_code_setting_fragment_dialog_confirm_title),
        getString(R.string.pin_code_setting_fragment_dialog_confirm_message),
        getString(R.string.com_ok)
    )
        .show(activity?.supportFragmentManager, "")
  }

  private fun setupButtons() {
    button1.setOnClickListener {
      addPinCode(1)
    }
    button2.setOnClickListener {
      addPinCode(2)
    }
    button3.setOnClickListener {
      addPinCode(3)
    }
    button4.setOnClickListener {
      addPinCode(4)
    }
    button5.setOnClickListener {
      addPinCode(5)
    }
    button6.setOnClickListener {
      addPinCode(6)
    }
    button7.setOnClickListener {
      addPinCode(7)
    }
    button8.setOnClickListener {
      addPinCode(8)
    }
    button9.setOnClickListener {
      addPinCode(9)
    }
    button0.setOnClickListener {
      addPinCode(0)
    }
    backButton.setOnClickListener {
      if (activity is BaseFragmentActivity) {
        (activity as BaseFragmentActivity).onBackPressed()
      }
    }
  }

  private fun changeCircleState() {
    context?.let {
      view?.findViewById<View>(circleList[stringBuilder.length - 1])
          ?.background = it.getDrawable(R.drawable.frame_round_gray)
    }
  }

  companion object {
    private const val PIN_MAX_LENGTH = 6
    private const val ARG_IS_CONFIRM_MODE = "is_confirm_mode"
    private const val ARG_PIN_CODE = "pin_code"
    fun newInstance(
      isConfirmMode: Boolean = false,
      pinCode: String = ""
    ): PinCodeInputFragment {
      val fragment = PinCodeInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, DEFAULT_VALUE_VISIBLE_TOOLBAR)
        putBoolean(ARG_IS_CONFIRM_MODE, isConfirmMode)
        putString(ARG_PIN_CODE, pinCode)
      }
      return fragment
    }
  }
}