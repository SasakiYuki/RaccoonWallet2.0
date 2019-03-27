package wallet.raccoon.raccoonwallet.view.fragment.send

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_send_message_input.closeButton
import kotlinx.android.synthetic.main.fragment_send_message_input.editText
import kotlinx.android.synthetic.main.fragment_send_message_input.textCountTextView
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class SendMessageInputFragment : BaseFragment() {
  private val compositeDisposable = CompositeDisposable()
  override fun layoutRes() = R.layout.fragment_send_message_input

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    closeButton.setOnClickListener {
      activity?.onBackPressed()
    }
    RxTextView.textChanges(editText)
        .map { item -> item.length }
        .subscribe { item ->
          item?.let {
            textCountTextView.text = getString(R.string.activity_send_mosaic_prefix, it)
          }
        }
        .let {
          compositeDisposable.add(it)
        }
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.dispose()
  }

  companion object {
    fun newInstance(): SendMessageInputFragment {
      val fragment = SendMessageInputFragment()
      fragment.arguments = Bundle().apply {
        putInt(ARG_CONTENTS_NAME_ID, DEFAULT_VALUE_VISIBLE_TOOLBAR)
      }
      return fragment
    }
  }
}