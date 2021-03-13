package com.example.servicepoc.androidapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class LocationForegroundService : Service() {

    companion object {
        private const val CHANNEL = "channel1"
        private const val TRACKING_NOTIFICATION_ID = 1
        fun startService(context: Context) {
            val startIntent = Intent(context, LocationForegroundService::class.java)

            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, LocationForegroundService::class.java)
            context.stopService(stopIntent)
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        createNotification()
        return START_STICKY
    }

    private fun createNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            0
        )

        val notification = NotificationCompat.Builder(this, CHANNEL)
            .setChannelId(CHANNEL)
            .setContentTitle("Test")
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(TRACKING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL,
                "Foreground Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            assert(manager != null)
            manager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }
    override fun onBind(intent: Intent?): IBinder? = null
}