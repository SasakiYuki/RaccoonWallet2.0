package wallet.raccoon.raccoonwallet.flux.getter

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.reducer.ProfileAddressAddReducer
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.ProfileAddressAddActionType
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.repository.MyProfileRepository

class ProfileAddressAddActionCreator(
  private val repository: MyProfileRepository,
  private val dispatch: (ProfileAddressAddActionType) -> Unit,
  val reducer: ProfileAddressAddReducer
) : DisposableMapper() {

  suspend fun insert(
    context: Context,
    walletInfo: WalletInfo
  ) {
    Network.request(
        context,
        repository.insertWalletInfoAsync(walletInfo),
        {
          dispatch(ProfileAddressAddActionType.Insert(it))
        }, {
      it.printStackTrace()
    })
  }

  suspend fun update(
    context: Context,
    walletInfo: WalletInfo
  ) {
    Network.request(
        context,
        repository.insertWalletInfoAsync(walletInfo),
        {
          dispatch(ProfileAddressAddActionType.Update(it))
        }, {
      it.printStackTrace()
    }
    )
  }

}
