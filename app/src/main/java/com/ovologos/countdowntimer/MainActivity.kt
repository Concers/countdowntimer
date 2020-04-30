package com.ovologos.countdowntimer

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private var isCancelled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val minute: Long = 1000

        val millisInFuture: Long = minute * 1500
        val countDownInterval: Long = 1000

        text_view.text = timeString(millisInFuture)


        button_start.setOnClickListener {
            timer(millisInFuture, countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
            isCancelled = false


        }
        button_stop.setOnClickListener {
            isCancelled = true
            button_stop.isEnabled = false
            it.isEnabled = false
            button_start.isEnabled = true
        }


    }

    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {

        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                text_view.text = "Done"
                button_start.isEnabled = true
                button_start.isEnabled = false
            }

            override fun onTick(millisUntilFinished: Long) {

                val timeReaming = timeString(millisUntilFinished)
                if (isCancelled) {
                    text_view.text = "${text_view.text}\nStopped"
                    cancel()
                } else {
                    text_view.text = timeReaming
                }

            }
        }
    }


    private fun timeString(millisUntilFinished: Long): String {
        var millisUntilFinished: Long = millisUntilFinished


        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

        // Format the string
        return String.format(
            Locale.getDefault(),
            "%02d min: %02d sec",
            minutes, seconds
        )

    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}


