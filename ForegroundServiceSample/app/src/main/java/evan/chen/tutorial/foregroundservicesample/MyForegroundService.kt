package evan.chen.tutorial.foregroundservicesample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    private val CHANNEL_ID = "ForegroundServiceChannel"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action == "STOP") {
            stopSelf()
            return START_NOT_STICKY
        }

        val notification = createNotification()

        startForeground(1, notification)

        //持續性工作
        Thread {
            Thread.sleep(50000)
        }.start()

        return START_NOT_STICKY
    }

    private fun createNotification(): Notification {
        createNotificationChannel()

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val stopSelfIntent = Intent(this, MyForegroundService::class.java)
        stopSelfIntent.action = "STOP"

        val pendingStopSelfIntent: PendingIntent =
            PendingIntent.getService(this, 0, stopSelfIntent, PendingIntent.FLAG_IMMUTABLE)

        val action =
            NotificationCompat.Action.Builder(
                R.drawable.baseline_stop_24,
                "Stop",
                pendingStopSelfIntent
            ).build()

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("service is running")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(pendingIntent)
            .addAction(action)
            .setTicker("ticker")
            .build()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(serviceChannel)
    }
}