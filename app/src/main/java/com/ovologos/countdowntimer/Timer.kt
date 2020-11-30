package com.ovologos.countdowntimer

import android.os.CountDownTimer
import java.text.DecimalFormat


class Timer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(
    millisInFuture,
    countDownInterval
) {
    override fun onTick(millisUntilFinished: Long) {

        val timeFormat = DecimalFormat("00")
        val hour: Long = (millisUntilFinished / 3600) % 24
        val min: Long = (millisUntilFinished / 6000) % 60
        val sec: Long = (millisUntilFinished / 1000) % 60



        TODO("Not yet implemented")
    }

    override fun onFinish() {
        TODO("Not yet implemented")
    }

}