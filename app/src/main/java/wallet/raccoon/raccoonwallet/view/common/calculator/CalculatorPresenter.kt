package wallet.raccoon.raccoonwallet.view.common.calculator

import wallet.raccoon.raccoonwallet.type.CalculatorNumberType

interface CalculatorPresenter {

  fun typeNumber(key: CalculatorNumberType)

  fun add()

  fun minus()

  fun divide()

  fun multiply()

  fun typeDot()

  fun typeEquals()

  fun backspace()

  fun exit()

  fun ce()
}

