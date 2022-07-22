package com.example.notification

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyNotificationManager.createNotificationChannel(this)
    }
}