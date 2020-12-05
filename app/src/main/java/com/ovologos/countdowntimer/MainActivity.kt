package com.ovologos.countdowntimer

import android.media.MediaPlayer
import android.os.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis: Long = 0
    lateinit var player: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val millisInFuture: Long = 3000
        val countDownInterval: Long = 1000


// Count down timer start button
        button_start.setOnClickListener {
            // Start the timer
            timer(millisInFuture, countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
            button_pause.isEnabled = true
            isCancelled = false
            isPaused = false
        }


// Count down timer stop/cancel button
        button_stop.setOnClickListener {
            // Start the timer
            isCancelled = true
            isPaused = false
            it.isEnabled = false
            button_start.isEnabled = true
            button_pause.isEnabled = false
        }


// Count down timer pause button
        button_pause.setOnClickListener {
            isPaused = true
            isCancelled = false
            it.isEnabled = false
            button_start.isEnabled = false
            button_stop.isEnabled = false
            button_resume.isEnabled = true
        }


// Count down timer resume button
        button_resume.setOnClickListener {
            // Resume the timer
            timer(resumeFromMillis, countDownInterval).start()
            isPaused = false
            isCancelled = false
            it.isEnabled = false
            button_pause.isEnabled = true
            button_start.isEnabled = false
            button_stop.isEnabled = true
        }
    }


    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

                val timeRemaining = (millisUntilFinished / 1000).toString()

                val hms = String.format(
                    "%02d:%02d:%02d:%02d",
                    TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    (TimeUnit.MILLISECONDS.toHours(millisUntilFinished) -
                            TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))),
                    (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                            TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(
                                    millisUntilFinished
                                )
                            )),
                    (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    ))
                )
                val StringFormat = String.format((millisInFuture / 60).toString())


// Resume and Pause Checked
                if (isPaused) {
                    text_view.text = hms
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                } else if (isCancelled) {
                    text_view.text = hms
                    cancel()
                } else {
                    text_view.text = hms
                }
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFinish() {
                val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {

                    vibrator.run {
                        1500
                    }
                }

                text_view.text = "Yeniden başlayınız "
                button_start.isEnabled = true
                button_stop.isEnabled = false


            }
        }


    }

}







