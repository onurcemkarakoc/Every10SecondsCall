package com.onurcemkarakoc.every10secondscall

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

class MyService: Service() {

    private val CHANNEL_ID = "ForegroundService Kotlin"

    private val myRepository: MyRepository by lazy { MyRepository() }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)

            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendDataFromService()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("MyService is running")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }




    private fun sendDataFromService() {

        val timer = object : CountDownTimer(10_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                myRepository.getJoke()
                myRepository.jokeModelCallback = {
                    sendDataFromService()
                    getJokeCallback?.invoke(it)
                }
            }
        }
        timer.start()
    }









    companion object {
        var getJokeCallback: ((JokeModel) -> Unit)? = null

        fun startService(context: Context) {
            ContextCompat.startForegroundService(context, Intent(context, MyService::class.java))
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, MyService::class.java)
            context.stopService(stopIntent)
            stopService(context)
        }
    }
}