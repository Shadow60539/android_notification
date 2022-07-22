package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

object MyNotificationManager {

    val CHANNEL_ID = "channelId"
    val NOTIFICATION_ID = 0
    val CHANNEL_NAME = "channelName"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun getNotificationBuilder(
        context: Context,
        title: String,
        text: String,
        icon: Int,
    ): NotificationCompat.Builder {

        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setContentTitle(title)
            setContentText(text)
            setSmallIcon(icon)
        }

        return builder
    }
}
