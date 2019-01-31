package wallet.raccoon.raccoonwallet.view.controller

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import wallet.raccoon.raccoonwallet.MainActivity
import wallet.raccoon.raccoonwallet.model.DrawerEntity
import wallet.raccoon.raccoonwallet.model.DrawerType
import wallet.raccoon.raccoonwallet.model.epoxy.DrawerHeaderModel_
import wallet.raccoon.raccoonwallet.model.epoxy.DrawerRowModel_
import wallet.raccoon.raccoonwallet.model.epoxy.DrawerSectionModel_
import wallet.raccoon.raccoonwallet.viewmodel.MainActivityViewModel

class DrawerListController(
  activity: MainActivity,
  val name: String,
  val screenPath: String = "",
  val iconPath: String = ""
) : TypedEpoxyController<List<DrawerEntity>>() {
  @AutoModel
  lateinit var drawerHeaderModel: DrawerHeaderModel_

  @AutoModel
  lateinit var drawerSectionModel: DrawerSectionModel_

  private lateinit var mainActivityViewModel: MainActivityViewModel

  init {
    mainActivityViewModel = ViewModelProviders.of(activity)
        .get(MainActivityViewModel::class.java)
  }

  override fun buildModels(data: List<DrawerEntity>?) {
    data?.let {
      addList(it)
    }
  }

  private fun addList(data: List<DrawerEntity>) {
    drawerHeaderModel
        .name(name)
        .screenPath(screenPath)
        .iconPath(iconPath)
        .onClickHeaderListener(View.OnClickListener {
          //          listener.onHeaderClick()
          // TODO Header
        })
        .addTo(this)

    val mainList = ArrayList<DrawerEntity>()
    data.filterTo(mainList) { it.drawerType == DrawerType.MAIN }
    val subList = ArrayList<DrawerEntity>()
    data.filterTo(subList) { it.drawerType == DrawerType.SUB }

    for (item in mainList) {
      DrawerRowModel_()
          .id(modelCountBuiltSoFar)
          .drawerEntity(item)
          .onClickRowListener(View.OnClickListener {
            mainActivityViewModel.navigationClickEvent.postValue(item)
          })
          .addTo(this)
    }

    drawerSectionModel.addTo(this)

    for (item in subList) {
      DrawerRowModel_()
          .id(modelCountBuiltSoFar)
          .drawerEntity(item)
          .onClickRowListener(View.OnClickListener {
            //            listener.onRowClick(item)
            // TODO ROW
          })
          .addTo(this)
    }

  }

  interface OnDrawerClickListener {
    fun onHeaderClick()
    fun onRowClick(drawerEntity: DrawerEntity)
  }
}
