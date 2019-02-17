package wallet.raccoon.raccoonwallet.store.creator

import android.content.Context
import wallet.raccoon.raccoonwallet.flux.DisposableMapper
import wallet.raccoon.raccoonwallet.network.Network
import wallet.raccoon.raccoonwallet.store.type.HomeFragmentActionType
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
    dispatch(HomeFragmentActionType.WalletEntity(useCase.getWallet(walletId)))
  }
}