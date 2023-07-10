package evan.chen.tutorial.arch.alarmmanagersample

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.cancelButton
import kotlinx.android.synthetic.main.activity_main.pickTimeButton
import kotlinx.android.synthetic.main.activity_main.textView
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickTimeButton.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)

            TimePickerDialog(
                this,
                this,
                hour,
                minute,
                android.text.format.DateFormat.is24HourFormat(this)
            ).show()

        }

        cancelButton.setOnClickListener {
            cancelAlarm()
        }

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        textView.text =
            "已設定: ${DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)}"

        startAlarm(calendar)
    }

    private fun startAlarm(calendar: Calendar) {
        val calendar =  Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 14)
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //傳遞參數
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra("A", "Test")

        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, FLAG_IMMUTABLE)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis, pendingIntent
        )

//        val intervalMillis: Long = 1000 * 60 * 60  // 間隔設為 1 小時
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            intervalMillis,
//            pendingIntent)


        // 設定要在1小內間隔內觸發
        val windowLengthMillis: Long = 1000 * 60 * 60

        alarmManager.setWindow(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            windowLengthMillis,
            pendingIntent)

    }

    private fun cancelAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)

        textView.text = "Alarm 已取消"
    }
}