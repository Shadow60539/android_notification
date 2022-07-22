package com.example.notification

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun onSimpleNotificationPressed(view: View) {

        val NOTIFICATION_ID = 0

        val notification = MyNotificationManager.getNotificationBuilder(
            this,
            "Simple Notification",
            "Text",
            R.drawable.ic_launcher_foreground,
        )

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification.build())
    }

    fun onProgressNotificationPressed(view: View) {
        startService(Intent(this, DownloadService::class.java))
    }

    fun onActionNotificationPressed(view: View) {

        val NOTIFICATION_ID = 2

        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )

        val notification = MyNotificationManager.getNotificationBuilder(
            this,
            "Action Notification",
            "Text",
            R.drawable.ic_launcher_foreground,
        ).apply {
            addAction(R.drawable.ic_send, "Send", pendingIntent)
        }

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification.build())
    }

    fun onGroupNotificationPressed(view: View) {

        val GROUP_KEY = "GROUP_KEY"

        listOf('A', 'B', 'C', 'D').forEachIndexed { index, value ->
            val notification = MyNotificationManager.getNotificationBuilder(
                this,
                "Person $value",
                "Message from Person $value",
                R.drawable.ic_launcher_foreground,
            ).apply {
                setGroup(GROUP_KEY)
            }

            NotificationManagerCompat.from(this)
                .notify(listOf(4, 5, 6, 7)[index], notification.build())
        }
    }

    fun onExpandableNotificationPressed(view: View) {

        val NOTIFICATION_ID = 8

        val notification = MyNotificationManager.getNotificationBuilder(
            this,
            "Expandable Notification",
            "Text",
            R.drawable.ic_launcher_foreground,
        ).apply {
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
            )
        }

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification.build())
    }

}

