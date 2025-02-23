package com.example.androidpracticumcustomview.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    private val animationDuration: Long = 5000,
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val parentWidth = right - left
        val parentHeight = bottom - top

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val childLeft = (parentWidth - childWidth) / 2
            val childTop = if (i == 0) 0 else parentHeight - childHeight

            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)

            Log.d(
                "measures",
                "child.layout(left: $childLeft, top: $childTop, right: ${childLeft + childWidth}, bottom: ${childTop + childHeight}"
            )
        }
    }

    override fun addView(child: View) {
        if (childCount >= 2) {
            throw IllegalStateException("CustomContainer не может содержать более двух дочерних элементов")
        }


        val finalParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ) // для размещения в элементов центре, тк ViewGroup.TEXT_ALIGNMENT_CENTER не дает нужного результата



        post {
            val startY = if (childCount == 1) height / 2f else -height / 2f


            child.alpha = 0f
            child.translationY = startY

            val endY = child.top.toFloat()
            Log.d("measures", "addView: startY - $startY endY - $endY")
            Log.d("measures", "addView: height - $height")
            child.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .translationY(endY)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }.apply {
            super.addView(child, finalParams)
        }
    }
}
