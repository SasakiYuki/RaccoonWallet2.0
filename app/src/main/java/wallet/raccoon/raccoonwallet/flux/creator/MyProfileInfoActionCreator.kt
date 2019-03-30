package wallet.raccoon.raccoonwallet.flux.creator

import io.reactivex.Observable.empty
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.MyProfileInfoActionType
import wallet.raccoon.raccoonwallet.model.MyProfileEntity
import wallet.raccoon.raccoonwallet.usecase.MyProfileInfoUseCase

class MyProfileInfoActionCreator(
  private val useCase: MyProfileInfoUseCase,
  private val dispatch: (MyProfileInfoActionType) -> Unit
) : DisposableMapper() {
  private val myAddressCountSubject: PublishSubject<Unit> = PublishSubject.create()
  private val myProfileSubject: PublishSubject<Unit> = PublishSubject.create()
  private val updateSubject: PublishSubject<MyProfileEntity> = PublishSubject.create()

  init {
    myAddressCountSubject
        .flatMap {
          useCase.countAllMyAddress()
              .toObservable()
              .doOnNext {
                dispatch(MyProfileInfoActionType.WalletInfoCount(it))
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }
    myProfileSubject
        .flatMap {
          useCase.loadMyProfile()
              .toObservable()
              .doOnNext {
                dispatch(MyProfileInfoActionType.ReceiveMyProfile(it))
              }
              .doOnError {
                // do nothing
              }
              .onErrorResumeNext(empty())
              .subscribeOn(Schedulers.io())
        }
        .subscribe()
        .let { disposables.add(it) }
    updateSubject
        .flatMap {
          useCase.updateMyProfile(it)
              .toObservable()
              .doOnNext {
                dispatch(MyProfileInfoActionType.UpdateMyProfile(it))
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

  fun countUpMyAddress() {
    myAddressCountSubject.onNext(Unit)
  }

  fun loadMyProfile() {
    myProfileSubject.onNext(Unit)
  }

  fun updateMyProfile(entity: MyProfileEntity) {
    updateSubject.onNext(entity)
  }
}
