package com.vmmontes.excurrency.presentation.ui.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout
import com.vmmontes.excurrency.R

const val MAX_ANGLE_ROTATION = 360.0F
const val ANIMATION_TIME_IN_MILISECONDS: Long = 2000

class RotatingProgressBarView: FrameLayout {

    var canvasBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_white_loading)
                        .copy(Bitmap.Config.ARGB_8888, true)
    lateinit var bitmap: Bitmap
    var currentAngleValue = 0.0F
    var isBitmapInit = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    fun init() {
        setWillNotDraw(false)
        configureAndStartAnimation()
    }

    private fun configureAndStartAnimation() {
        if (width > 0 && height > 0) {
            bitmap = Bitmap.createScaledBitmap(canvasBitmap, width, height, false)
            isBitmapInit = true
            animationStart()
        }
    }

    fun animationStart() {
        val animator = ValueAnimator.ofFloat(0.0F, MAX_ANGLE_ROTATION)
        animator.duration = ANIMATION_TIME_IN_MILISECONDS

        animator.addUpdateListener{
            val animatiorValue = it.animatedValue as Float
            currentAngleValue = animatiorValue
            invalidate()
        }

        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (width > 0 && height > 0 && isBitmapInit) {
            canvas?.rotate(currentAngleValue, (width / 2).toFloat(), (height / 2).toFloat())
            canvas?.drawBitmap(bitmap, 0.0F, 0.0F, null)

            if (currentAngleValue >= MAX_ANGLE_ROTATION) {
                animationStart()
            }
        } else {
            configureAndStartAnimation()
        }
    }

}