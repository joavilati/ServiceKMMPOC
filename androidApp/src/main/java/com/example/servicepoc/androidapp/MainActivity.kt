package com.example.servicepoc.androidapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.servicepoc.androidapp.databinding.ActivityMainBinding
import com.example.servicepoc.shared.Greeting
import kotlinx.coroutines.Runnable
import java.util.concurrent.TimeUnit


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var alarmManager: AlarmManager
    private var pendingIntent: PendingIntent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnService.setOnClickListener {
            LocationForegroundService.startService(this)
        }
    }

    override fun onDestroy() {
        LocationForegroundService.stopService(this)
        super.onDestroy()
    }
}
