//package com.example.timetable
//
//import android.app.AlarmManager
//import android.app.PendingIntent
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.media.MediaPlayer
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.TimePicker
//import android.widget.Toast
//import java.util.Calendar
//
//class AlarmNPendingIntentDemo : AppCompatActivity() {
//    lateinit var timePicker:TimePicker
//    lateinit var setAlarm:Button
//    lateinit var cancelButton:Button
//    lateinit var alarmManager:AlarmManager
//    lateinit var pendingIntent:PendingIntent
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_alarm_manager_android)
//        timePicker=findViewById(R.id.timePicker)
//        setAlarm=findViewById(R.id.buttonAlarm)
//        cancelButton=findViewById(R.id.cancel)
//        setAlarm.setOnClickListener {
//            val calendar:Calendar=Calendar.getInstance()
//            if(Build.VERSION.SDK_INT>=23)
//            {
//                calendar.set(
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    timePicker.hour,
//                    timePicker.minute,
//                    0) }
//            else
//            {
//                calendar.set(
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    timePicker.currentHour,
//                    timePicker.currentMinute, 0
//                )
//            }
//            setAlarm(calendar.timeInMillis)
//        }
//        cancelButton.setOnClickListener {
//            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//            alarmManager.cancel(pendingIntent)
//            Toast.makeText(this,"Alarm Cancelled", Toast.LENGTH_SHORT).show()
//        }
//    }
//    fun setAlarm(timeInMillis:Long)
//    {
//        alarmManager=getSystemService(Context.ALARM_SERVICE)as AlarmManager
//        val intent=Intent(this,MyAlarmDemo::class.java)
//        pendingIntent=PendingIntent.getBroadcast(this,
//            0,intent,0)
//        alarmManager.setRepeating(AlarmManager.RTC,
//            timeInMillis,
//            AlarmManager.INTERVAL_DAY,pendingIntent)
//        Toast.makeText(this,"Alarm is Set",Toast.LENGTH_LONG).show()
//    }
//
//    class MyAlarmDemo:BroadcastReceiver(){
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            var mp = MediaPlayer.create(p0, R.raw.alarm)
//
//            Log.d("Hello","repeating alarm")
//            mp.start()
//            Toast.makeText(p0,"Alarm is Ringing",Toast.LENGTH_LONG).show()
//        }
//    }
//}
