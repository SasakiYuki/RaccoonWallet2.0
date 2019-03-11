package wallet.raccoon.raccoonwallet.view.common.calculator

import wallet.raccoon.raccoonwallet.type.OperatorType
import wallet.raccoon.raccoonwallet.view.common.BaseView

interface CalculationContract {

  interface View : BaseView<CalculatorPresenter> {
    fun updateDisplay(value: String)

    fun updateOperation(operation: OperatorType)

    fun finish()

    fun updateRightDisplay(value: String)

    fun updateNextButton()
  }
}
