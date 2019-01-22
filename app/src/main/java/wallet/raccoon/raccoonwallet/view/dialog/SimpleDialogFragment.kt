package wallet.raccoon.raccoonwallet.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment

abstract class SimpleDialogFragment : DialogFragment() {

  abstract fun setLayout(): Int

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = Dialog(activity)

    dialog.setContentView(setLayout())
    return dialog
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
  }
}
