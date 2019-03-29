package wallet.raccoon.raccoonwallet.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class ConstraintLayoutBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<ConstraintLayout>(context, attrs) {

    private var defaultTop: Int = 0
    private var defaultBottom: Int = 0
    private var defaultHeight: Int = 0

    override fun onLayoutChild(parent: CoordinatorLayout, child: ConstraintLayout, layoutDirection: Int): Boolean {
        defaultTop = child.top
        defaultBottom = child.bottom
        defaultHeight = defaultBottom - defaultTop
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, axes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, axes: Int) {
        ViewCompat.offsetTopAndBottom(child, dyConsumed)
        if (dyConsumed > 0 && child.top > defaultBottom) {
            child.top = defaultBottom
        } else if (child.top < defaultTop) {
            child.top = defaultTop
        }
        child.bottom = child.top + defaultHeight
    }
}
