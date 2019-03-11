package wallet.raccoon.raccoonwallet.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_calculation_amount.textDisplayRight
import kotlinx.android.synthetic.main.fragment_calculation_amount.txtDisplay
import kotlinx.android.synthetic.main.fragment_calculation_amount.txtSignals
import kotlinx.android.synthetic.main.view_calculator.view.btnBack
import kotlinx.android.synthetic.main.view_calculator.view.btnCE
import kotlinx.android.synthetic.main.view_calculator.view.btnDivide
import kotlinx.android.synthetic.main.view_calculator.view.btnDot
import kotlinx.android.synthetic.main.view_calculator.view.btnEight
import kotlinx.android.synthetic.main.view_calculator.view.btnEquals
import kotlinx.android.synthetic.main.view_calculator.view.btnFive
import kotlinx.android.synthetic.main.view_calculator.view.btnFour
import kotlinx.android.synthetic.main.view_calculator.view.btnMAX
import kotlinx.android.synthetic.main.view_calculator.view.btnMinus
import kotlinx.android.synthetic.main.view_calculator.view.btnMultiply
import kotlinx.android.synthetic.main.view_calculator.view.btnNine
import kotlinx.android.synthetic.main.view_calculator.view.btnOne
import kotlinx.android.synthetic.main.view_calculator.view.btnPlus
import kotlinx.android.synthetic.main.view_calculator.view.btnRightArrow
import kotlinx.android.synthetic.main.view_calculator.view.btnSeven
import kotlinx.android.synthetic.main.view_calculator.view.btnSix
import kotlinx.android.synthetic.main.view_calculator.view.btnThree
import kotlinx.android.synthetic.main.view_calculator.view.btnTwo
import kotlinx.android.synthetic.main.view_calculator.view.btnZero
import kotlinx.android.synthetic.main.view_calculator.view.pagerIndicator
import kotlinx.android.synthetic.main.view_calculator.view.wrapViewPager
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.model.local.MosaicItem
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.EIGHT
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.FIVE
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.FOUR
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.NINE
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.ONE
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.SEVEN
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.SIX
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.THREE
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.TWO
import wallet.raccoon.raccoonwallet.type.CalculatorNumberType.ZERO
import wallet.raccoon.raccoonwallet.type.OperatorType
import wallet.raccoon.raccoonwallet.view.adapter.CalculatorPagerAdapter
import wallet.raccoon.raccoonwallet.view.common.calculator.CalculationContract
import wallet.raccoon.raccoonwallet.view.common.calculator.CalculatorPresenter
import wallet.raccoon.raccoonwallet.view.common.calculator.CalculatorPresenterImpl
import wallet.raccoon.raccoonwallet.view.fragment.send.CalculationAmountFragment

class CalculatorView(
  context: Context?,
  attrs: AttributeSet?,
  defStyleAttr: Int
) :
    LinearLayout(context, attrs, defStyleAttr), CalculationContract.View {
  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : this(context, attrs, 0)

  constructor(context: Context?) : this(context, null, 0)

  override lateinit var presenter: CalculatorPresenter
  private var calculatorListener: OnClickMultiCalculator? = null
  lateinit var pagerAdapter: CalculatorPagerAdapter
  private val calculatedPair: HashMap<String, String> = HashMap()
  private var currentTxtDisplay: TextView? = null
  private var currentTxtSignals: TextView? = null
  private var currentTextDisplayRight: TextView? = null
  private var compositeDisposable: CompositeDisposable = CompositeDisposable()

  init {
    View.inflate(context, R.layout.view_calculator, this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    pagerAdapter =
      CalculatorPagerAdapter((context as AppCompatActivity).supportFragmentManager, ArrayList())
    pagerAdapter.add(MosaicItem.createNEMXEMItem())
    setupButtons()
    wrapViewPager.adapter = pagerAdapter
    pagerIndicator.setCount(pagerAdapter.count)
    wrapViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
      }

      override fun onPageSelected(position: Int) {
        presenter = CalculatorPresenterImpl(this@CalculatorView)
        setupCurrentTexts(position)
        if (pagerAdapter.getItem(position) != null) {
          val amount = calculatedPair[pagerAdapter.getItem(position).getFullName()]
          amount?.let {
            val charArray = it.toCharArray()
            for (item in charArray) {
              (presenter as CalculatorPresenterImpl).typeNumberFromString(item.toString())
            }
          }
        }
        pagerIndicator.setCurrentPosition(position)
      }

    })
    presenter = CalculatorPresenterImpl(this)
  }

  override fun updateDisplay(value: String) {
    if (pagerAdapter.fragments.size > 0 && value != "0") {
      val item = pagerAdapter.getItem(wrapViewPager.currentItem)
      calculatedPair[item.getFullName()] = value
    }
    currentTextDisplayRight?.visibility = View.GONE
    if (currentTxtDisplay == null) {
      setupCurrentTexts(wrapViewPager.currentItem)
    }
    currentTxtDisplay?.text = value
    if (value == "0") {
      if (pagerAdapter.fragments.size > 0) {
        val item = pagerAdapter.getItem(wrapViewPager.currentItem)
        calculatorListener?.onPutZERO(item)
      }
    } else {
      if (pagerAdapter.fragments.size > 0) {
        val item = pagerAdapter.getItem(wrapViewPager.currentItem)
        calculatorListener?.onPutNotZERO(item)
      }
    }
  }

  override fun updateOperation(operation: OperatorType) {
    currentTxtSignals?.let {
      when (operation) {
        OperatorType.NONE -> it.text = ""
        OperatorType.ADDITION -> it.setText(R.string.calculator_plus_sign)
        OperatorType.SUBTRACTION -> it.setText(R.string.calculator_minus_sign)
        OperatorType.MULTIPLICATION -> it.setText(R.string.calculator_multiplication_sign)
        OperatorType.DIVISION -> it.setText(R.string.calculator_division_sign)
      }
    }
    disableRightArrowButton()
  }

  override fun finish() {
  }

  override fun updateRightDisplay(value: String) {
    currentTextDisplayRight?.let {
      it.visibility = View.VISIBLE
      it.text = value
    }
    disableRightArrowButton()
  }

  override fun updateNextButton() {
    if (isEnableRightArrowButton()) {
      enableRightArrowButton()
    } else {
      disableRightArrowButton()
    }
  }

  private fun enableRightArrowButton() {
    btnRightArrow.setBackgroundColor(ContextCompat.getColor(context, R.color.nemOrange))
    btnRightArrow.isEnabled = true
  }

  private fun isEnableRightArrowButton(): Boolean {
    return if (currentTextDisplayRight != null && currentTxtSignals != null) {
      if (currentTextDisplayRight!!.visibility == View.VISIBLE) {
        false
      } else !currentTxtSignals!!.text.isNotEmpty()
    } else {
      false
    }
  }

  fun setupCurrentTexts(position: Int) {
    if (pagerAdapter.fragments.size > 0) {
      val fragment = pagerAdapter.getCurrentFragment(position)
      if (fragment != null && fragment is CalculationAmountFragment) {
        currentTxtDisplay = fragment.txtDisplay
        currentTxtSignals = fragment.txtSignals
        currentTextDisplayRight = fragment.textDisplayRight
      }
    }
  }

  private fun setupButtons() {
    btnZero.setOnClickListener {
      presenter.typeNumber(ZERO)
    }
    btnOne.setOnClickListener {
      presenter.typeNumber(ONE)
    }
    btnTwo.setOnClickListener {
      presenter.typeNumber(TWO)
    }
    btnThree.setOnClickListener {
      presenter.typeNumber(THREE)
    }
    btnFour.setOnClickListener {
      presenter.typeNumber(FOUR)
    }
    btnFive.setOnClickListener {
      presenter.typeNumber(FIVE)
    }
    btnSix.setOnClickListener {
      presenter.typeNumber(SIX)
    }
    btnSeven.setOnClickListener {
      presenter.typeNumber(SEVEN)
    }
    btnEight.setOnClickListener {
      presenter.typeNumber(EIGHT)
    }
    btnNine.setOnClickListener {
      presenter.typeNumber(NINE)
    }
    btnDot.setOnClickListener {
      presenter.typeDot()
    }
    btnCE.setOnClickListener {
      ce()
    }
    btnBack.setOnClickListener {
      presenter.backspace()
    }
    btnEquals.setOnClickListener {
      presenter.typeEquals()
    }
    btnPlus.setOnClickListener {
      presenter.add()
    }
    btnMinus.setOnClickListener {
      presenter.minus()
    }
    btnDivide.setOnClickListener {
      presenter.divide()
    }
    btnMultiply.setOnClickListener {
      presenter.multiply()
    }
    btnRightArrow.setOnClickListener {
      calculatorListener?.onClickRight(calculatedPair)
    }
    btnMAX.setOnClickListener {
      val item = pagerAdapter.getItem(wrapViewPager.currentItem)
      if (item.isNEMXEMItem()) {
        // TODO 自身の総量を取る　XEM
      } else {
        val charArray = item.quantity.toString()
            .toCharArray()
        for (item in charArray) {
          (presenter as CalculatorPresenterImpl).typeNumberFromString(item.toString())
        }
      }
    }
  }

  private fun ce() {
    presenter.ce()
    calculatedPair.remove(pagerAdapter.getItem(wrapViewPager.currentItem).getFullName())
  }

  private fun disableRightArrowButton() {
    btnRightArrow.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_medium))
    btnRightArrow.isEnabled = false
  }
}

interface OnClickMultiCalculator {
  fun onClickRight(hashMap: HashMap<String, String>)
  fun onPutZERO(mosaicItem: MosaicItem)
  fun onPutNotZERO(mosaicItem: MosaicItem)
}
