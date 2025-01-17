package com.arunabha.fcmtask

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("fcm", "Device toke: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if(isAppInForeGround()){
            NotificationHelper.showToast(applicationContext, "Foreground: $message")
        }else{
            NotificationHelper.showNotification(applicationContext, "FCM Notification", "Background: $message")
        }

        // Log.d("fcm", "Message received: ${message.notification?.body}")
    }

    private fun isAppInForeGround(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcess = activityManager.runningAppProcesses ?: return false
        val packageName = applicationContext.packageName
        for (appProcess in runningAppProcess) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                appProcess.processName == packageName
            ) {
                return true
            }
        }
        return false
    }
}