package wallet.raccoon.raccoonwallet.flux.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.db.Database
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.flux.type.HomeFragmentActionType
import wallet.raccoon.raccoonwallet.usecase.HomeFragmentUseCase

class HomeFragmentActionCreator(
  private val useCase: HomeFragmentUseCase,
  private val dispatch: (HomeFragmentActionType) -> Unit
) : DisposableMapper() {
  suspend fun loadAccountInfo(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getAccountInfo(address),
        {
          dispatch(HomeFragmentActionType.AccountInfo(it))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadTransactionList(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getAccountTransfersAll(address),
        {
          dispatch(HomeFragmentActionType.AllTransaction(it))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadHarvestInfo(
    context: Context,
    address: String
  ) {
    Network.request(
        context,
        useCase.getHarvestInfo(address),
        {
          dispatch(HomeFragmentActionType.HarvestInfo(it))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadZaifNemPrice(
    context: Context
  ) {
    Network.request(
        context,
        useCase.getNemPrice(),
        {
          dispatch(HomeFragmentActionType.ZaifNemPrice(it))
        }, {
      it.printStackTrace()
    }
    )
  }

  suspend fun loadWallet(
    walletId: Long
  ){
    Database.query(useCase.getWallet(walletId),{
      dispatch(HomeFragmentActionType.WalletEntity(it))
    })
  }
}