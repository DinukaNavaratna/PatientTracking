package com.patienttracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.app.AlarmManager
import android.app.PendingIntent
import android.widget.EditText
import android.widget.ToggleButton
import android.widget.TimePicker
import com.patienttracking.alarm.AlarmReceiver
import java.util.Calendar

class Reminders : AppCompatActivity() {
    var alarmTimePicker: TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reminders)
        alarmTimePicker = findViewById<View>(R.id.timePicker) as TimePicker
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    }

    // OnToggleClicked() method is implemented the time functionality
    fun OnToggleClicked(view: View) {
        var time: Long
        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this@Reminders, "ALARM ON", Toast.LENGTH_SHORT).show()
            val calendar: Calendar = Calendar.getInstance()

            // calendar is called to get current time in hour and minute
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker!!.currentHour)
            calendar.set(Calendar.MINUTE, alarmTimePicker!!.currentMinute)

            // using intent i have class AlarmReceiver class which inherits
            // BroadcastReceiver
            val intent = Intent(this, AlarmReceiver::class.java)
            var tit: EditText = findViewById(R.id.alarmtitle)
            intent.putExtra("title",tit.text.toString())
            intent.putExtra("body","Alarm Body")

            // we call broadcast using pendingIntent
            pendingIntent = PendingIntent.getBroadcast(this, (tit.text.toString()).toInt(), intent, 0)
            time = calendar.getTimeInMillis() - calendar.getTimeInMillis() % 60000
            if (System.currentTimeMillis() > time) {
                // setting time as AM and PM
                time =
                    if (calendar.get(Calendar.AM_PM) === 0) time + 1000 * 60 * 60 * 12 else time + 1000 * 60 * 60 * 24
            }
            // Alarm rings continuously until toggle button is turned off
            alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, time, 50000, pendingIntent)
            // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
        } else {
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(this@Reminders, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }
}