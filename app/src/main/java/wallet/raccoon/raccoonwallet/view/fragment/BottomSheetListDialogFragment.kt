package wallet.raccoon.raccoonwallet.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.extentions.getDensity

class BottomSheetListDialogFragment : BottomSheetDialogFragment() {

  private lateinit var label: String
  private var menuRes: Int = 0
  private lateinit var onClickListener: (BottomSheetListDialogFragment, Int) -> Unit
  private var deleteId = 0

  companion object {

    fun newInstance(label: String, menuRes: Int, deleteId: Int = 0, onClickListener: (BottomSheetListDialogFragment, Int) -> Unit = { _, _ -> }) = BottomSheetListDialogFragment().apply {
      arguments = Bundle()
      this.label = label
      this.menuRes = menuRes
      this.deleteId = deleteId
      this.onClickListener = onClickListener
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
    return super.onCreateDialog(savedInstanceState)
  }

  override fun setupDialog(dialog: Dialog?, style: Int) {
    val context = context ?: return
    if (menuRes == 0) return

    val container = View.inflate(context, R.layout.fragment_bottom_sheet_list_dialog, null) as LinearLayout

    val menu = PopupMenu(context, View(context)).menu.apply {
      MenuInflater(context).inflate(menuRes, this)
    }
    if (deleteId != 0) {
      menu.removeItem(deleteId)
    }

    (0 until menu.size())
        .map { menu.getItem(it) }
        .forEach { item ->
          (View.inflate(context, R.layout.row_bottom_sheet, null) as LinearLayout).let { row ->
            (row.findViewById<AppCompatImageView>(R.id.iconImageView) as AppCompatImageView).setImageDrawable(item.icon)
            (row.findViewById<TextView>(R.id.titleTextView) as AppCompatTextView).text = item.title
            row.setOnClickListener { onClickListener(this, item.itemId) }
            container.addView(row, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (48f * context.getDensity()).toInt()))
          }
        }

    dialog?.setContentView(container)
  }
}

