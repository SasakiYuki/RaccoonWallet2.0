package wallet.raccoon.raccoonwallet.flux.creator

import io.reactivex.Observable.empty
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.MyWalletInfoActionType
import wallet.raccoon.raccoonwallet.model.db.MyAddress
import wallet.raccoon.raccoonwallet.usecase.MyWalletInfoUseCase

class MyWalletInfoActionCreator(private val useCase: MyWalletInfoUseCase,
  private val dispatch: (MyWalletInfoActionType) -> Unit) : DisposableMapper() {

  private val myAddressSubject: PublishSubject<Unit> = PublishSubject.create()
  private val selectWalletInfoSubject: PublishSubject<Long> = PublishSubject.create()
  private val deleteMyAddressSubject: PublishSubject<MyAddress> = PublishSubject.create()

  init {
    myAddressSubject
        .flatMap {
          useCase.findAllMyAddress()
              .doOnNext {
                dispatch(MyWalletInfoActionType.ReceiveMyAddress(it))
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }

    selectWalletInfoSubject
        .flatMap {
          useCase.select(it)
              .toObservable()
              .doOnNext {
                dispatch(MyWalletInfoActionType.SelectWalletInfo(it))
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }

    deleteMyAddressSubject
        .flatMap {
          useCase.deleteMyAddress(it)
              .toObservable()
              .doOnNext {
                dispatch(MyWalletInfoActionType.DeleteMyAddress())
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

  fun findAllMyAddress() {
    myAddressSubject.onNext(Unit)
  }

  fun selectWalletInfo(id: Long) {
    selectWalletInfoSubject.onNext(id)
  }

  fun deleteMyAddress(myAddress: MyAddress) {
    deleteMyAddressSubject.onNext(myAddress)
  }

}
