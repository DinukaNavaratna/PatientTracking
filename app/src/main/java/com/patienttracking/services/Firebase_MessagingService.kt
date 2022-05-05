package com.patienttracking.services

import com.google.firebase.messaging.FirebaseMessagingService
import android.R
import android.app.Notification
import androidx.core.content.ContextCompat
import androidx.core.app.NotificationCompat
import android.app.NotificationChannel
import android.os.Build
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONObject
import com.google.firebase.messaging.RemoteMessage


class Firebase_MessagingService : FirebaseMessagingService() {

    override fun onNewToken(s: String) {
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", s)
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
        Log.i("NEW_TOKEN", "******************************")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val params = remoteMessage.data
        val `object` = JSONObject(params as Map<*, *>?)
        Log.e("JSON_OBJECT", `object`.toString())
        val NOTIFICATION_CHANNEL_ID = "FCM"
        val pattern = longArrayOf(0, 1000, 500, 1000)
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, "FCM",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = ""
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = pattern
            notificationChannel.enableVibration(true)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }

        // to diaplay notification in DND Mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
            channel.canBypassDnd()
        }
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)
            .setColor(ContextCompat.getColor(this, R.color.background_dark))
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_popup_reminder)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
        mNotificationManager.notify(1000, notificationBuilder.build())
    }
}