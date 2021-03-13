package com.example.servicepoc.androidapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import java.util.concurrent.TimeUnit

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        print("Ninja Start Alarm Receiver $intent")
        Toast.makeText(context, "TESTE ALARM $intent", Toast.LENGTH_LONG).show()
        startAlarmReceiver(context)
    }
    companion object {
        var intent: Intent? = null
        var pendingIntent: PendingIntent? = null

        fun startAlarmReceiver(context: Context?): PendingIntent? {
            if(intent == null && pendingIntent == null) {
                intent = Intent(context, AlarmReceiver::class.java)
                pendingIntent = PendingIntent.getBroadcast(context, 123, intent, 0)
            }
            configAlarmManager(context, pendingIntent)

            return pendingIntent
        }

        private fun configAlarmManager(
            context: Context?,
            pendingIntent: PendingIntent?
        ) {
            val alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + TimeUnit.SECONDS.toMillis(5),
                pendingIntent
            )
        }
    }
}