package wallet.raccoon.raccoonwallet.viewmodel

import io.reactivex.subjects.PublishSubject

class RaccoonPagerViewModel {
  val clickEvent: PublishSubject<RaccoonPagerType> = PublishSubject.create()

  fun onClickBottomButton() {
    onClickButton(RaccoonPagerType.BOTTOM_BUTTON)
  }

  private fun onClickButton(type: RaccoonPagerType) {
    clickEvent.onNext(type)
  }
}

enum class RaccoonPagerType {
  BOTTOM_BUTTON,
}
