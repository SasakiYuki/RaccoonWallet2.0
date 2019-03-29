package wallet.raccoon.raccoonwallet.flux.creator

import io.reactivex.Observable.empty
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.MyAddressProfileActionType
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.model.db.WalletInfo
import wallet.raccoon.raccoonwallet.usecase.MyAddressProfileUseCase

class MyAddressProfileActionCreator(
  private val useCase: MyAddressProfileUseCase,
  private val dispatch: (MyAddressProfileActionType) -> Unit
) : DisposableMapper() {
  private val insertMyAddressSubject: PublishSubject<MyAddress> = PublishSubject.create()
  private val insertWalletInfoSubject: PublishSubject<WalletInfo> = PublishSubject.create()

  init {
    insertMyAddressSubject
        .flatMap {
          useCase.insertMyAddress(it)
              .toObservable<Unit>()
              .doOnComplete {
                dispatch(MyAddressProfileActionType.InsertMyAddress())
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }

    insertWalletInfoSubject
        .flatMap {
          useCase.insertWalletInfo(it)
              .toObservable()
              .doOnNext {
                dispatch(MyAddressProfileActionType.InsertWalletInfo(it))
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }
  }

  fun insertMyAddress(myAddress: MyAddress) {
    insertMyAddressSubject.onNext(myAddress)
  }

  fun insertWalletInfo(walletInfo: WalletInfo) {
    insertWalletInfoSubject.onNext(walletInfo)
  }
}
