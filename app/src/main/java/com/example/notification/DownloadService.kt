package com.example.notification

import android.app.IntentService
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

class DownloadService : IntentService(null) {

    val MAX_PROGRESS = 100
    val NOTIFICATION_ID = 2

    private val builder = MyNotificationManager.getNotificationBuilder(
        this,
        "App Name",
        "Downloading...",
        R.drawable.ic_launcher_foreground,
    )

    override fun onHandleIntent(p0: Intent?) {

        NotificationManagerCompat.from(this).apply {
            builder.setProgress(MAX_PROGRESS, 0, false)
            notify(NOTIFICATION_ID, builder.build())
        }

        for (i in 0..10) {
            NotificationManagerCompat.from(this).apply {
                builder.setProgress(MAX_PROGRESS, i * 10, false)
                notify(NOTIFICATION_ID, builder.build())
            }
            Thread.sleep(500)
        }

        NotificationManagerCompat.from(this).apply {
            builder.apply {
                setProgress(0, 0, true)
                setContentText("Installing")
            }
            notify(NOTIFICATION_ID, builder.build())
        }

        Thread.sleep(1000)

        NotificationManagerCompat.from(this).apply {
            builder.apply {
                setProgress(0, 0, false)
                setContentText("Installed")
            }
            notify(NOTIFICATION_ID, builder.build())
        }

        stopSelf()
    }

}