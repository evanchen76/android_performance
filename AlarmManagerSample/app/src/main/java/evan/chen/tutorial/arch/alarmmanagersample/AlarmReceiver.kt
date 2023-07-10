package evan.chen.tutorial.arch.alarmmanagersample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val channelID = "channelID"
        const val channelName = "channelName"
    }

    override fun onReceive(context: Context, intent: Intent?) {

        val string = intent?.getStringExtra("A")
        println(string)

        notify(context)
    }

    private fun notify(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val serviceChannel = NotificationChannel(
            channelID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager =
            getSystemService(context, NotificationManager::class.java) as NotificationManager
        manager.createNotificationChannel(serviceChannel)

        val notification = NotificationCompat.Builder(
            context,
            channelID
        )
            .setContentTitle("Alarm Title!")
            .setContentText("收到Alarm")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .build()

        notificationManager.notify(1, notification)
    }

}