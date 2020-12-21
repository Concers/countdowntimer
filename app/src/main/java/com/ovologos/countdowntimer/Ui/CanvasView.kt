package com.ovologos.countdowntimer.Ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint: Paint = Paint()
        paint.strokeWidth = 5F
//        var transparentPaint = Paint()
//        transparentPaint.setColor(resources.getColor(R.color.transparent))
//        transparentPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
//        transparentPaint.setAntiAlias(true)
        paint.setARGB(80, 255, 255, 255)
        // canvas?.drawColor(Color.argb(100, 255, 255, 255));
        canvas?.drawCircle(250F, 250F, 225F, paint)

        paint.setARGB(75, 255, 255, 255)

        // canvas?.drawColor(Color.argb(100, 255, 255, 255));
        canvas?.drawCircle(250F, 250F, 175F, paint)

        paint.setARGB(55, 255, 255, 255)

        // canvas?.drawColor(Color.argb(100, 255, 255, 255));
        canvas?.drawCircle(250F, 250F, 250F, paint)


    }

}