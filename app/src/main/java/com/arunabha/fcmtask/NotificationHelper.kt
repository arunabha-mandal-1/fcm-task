package com.arunabha.fcmtask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat

object NotificationHelper {
    private const val CHANNEL_ID = "FCM_CHANNEL"

    // foreground
    fun showToast(context: Context, msg: String){
        Handler(Looper.getMainLooper()).post{
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    // background
    fun showNotification(context: Context, title: String, msg: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID, "FCM_NOTIFICATIONS",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(msg)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        notificationManager.notify(0, notification)
    }
}