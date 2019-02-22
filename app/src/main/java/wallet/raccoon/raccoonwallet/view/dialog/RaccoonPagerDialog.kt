package wallet.raccoon.raccoonwallet.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.ViewPagerIndicator
import wallet.raccoon.raccoonwallet.view.adapter.DialogPagerAdapter
import wallet.raccoon.raccoonwallet.view.fragment.SimpleMessageFragment
import wallet.raccoon.raccoonwallet.viewmodel.RaccoonPagerViewModel

class RaccoonPagerDialog : DialogFragment() {
  private lateinit var viewModel: RaccoonPagerViewModel
  private val list = ArrayList<Fragment>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.dialog_simple_pager, null, false)
    view?.let {
      setupMessagePager(it)
      setupTitle(it)
      setupButtons(it)
    }
    return view
  }

  private fun setupMessagePager(view: View) {
    val adapter = DialogPagerAdapter(list, childFragmentManager)
    val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
    viewPager.adapter = adapter
    val indicator = view.findViewById<ViewPagerIndicator>(R.id.pagerIndicator)
    indicator.setCount(adapter.count)
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
        indicator.setCurrentPosition(position)
      }

      override fun onPageSelected(position: Int) {
      }
    })
  }

  private fun setupTitle(view: View) {
    view.findViewById<TextView>(R.id.titleTextView)
        .text = getTitle()
  }

  private fun setupButtons(view: View) {
    val button = view.findViewById<Button>(R.id.button)
    button.text = getButtonText()
    button.setOnClickListener {
      viewModel.onClickBottomButton()
      dismiss()
    }
  }

  private fun getTitle() = arguments?.getString(ARG_TITLE, "")
  private fun getButtonText() = arguments?.getString(ARG_BUTTON_TEXT, "")

  companion object {
    private const val ARG_TITLE = "title"
    private const val ARG_BUTTON_TEXT = "button_text"
    fun createDialog(
      viewModel: RaccoonPagerViewModel,
      title: String,
      buttonText: String,
      messageFragmentList: ArrayList<SimpleMessageFragment>
    ): RaccoonPagerDialog {
      val dialog = RaccoonPagerDialog()
      dialog.viewModel = viewModel
      dialog.list.addAll(messageFragmentList)
      val args = Bundle().apply {
        putString(ARG_TITLE, title)
        putString(ARG_BUTTON_TEXT, buttonText)
      }
      dialog.arguments = args
      return dialog
    }
  }
}
