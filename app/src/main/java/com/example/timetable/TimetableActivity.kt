

//package com.example.timetable
//
package com.example.timetable

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateUtils.getDayOfWeekString
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.time.DayOfWeek

class TimetableActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    var dayOfWeek : String = "Tuesday"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        // Set up the day of the week at the top
        val btn1=findViewById<Button>(R.id.btn1)
        val btn2=findViewById<Button>(R.id.btn2)
        val btn3=findViewById<Button>(R.id.btn3)
        val btn4=findViewById<Button>(R.id.btn4)
        val btn5=findViewById<Button>(R.id.btn5)

        btn1.setOnClickListener{
            dayOfWeek=btn1.text.toString()
            refresh()
        }
        btn2.setOnClickListener{
            dayOfWeek=btn2.text.toString()
            refresh()
        }
        btn3.setOnClickListener{
            dayOfWeek=btn3.text.toString()
            refresh()
        }
        btn4.setOnClickListener{
            dayOfWeek=btn4.text.toString()
            refresh()
        }
        btn5.setOnClickListener{
            dayOfWeek=btn5.text.toString()
            refresh()
        }

//
////        // Get the current day of the week
////        val dayOfWeek = getDayOfWeek()
//
//        dayOfWeekTextView.text = dayOfWeek

//        dayOfWeekTextView.setText = dayOfWeek



        val entries = readJsonFile(dayOfWeek)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = TimetableAdapter(entries)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Set up a button to add a new entry
        val addButton: FloatingActionButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            // Create an intent to launch the Add/Edit screen
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }
//    private fun getDayOfWeek(): String {
//        val calendar = Calendar.getInstance()
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
//
//        return when (dayOfWeek) {
//            Calendar.MONDAY -> "Monday"
//            Calendar.TUESDAY -> "Tuesday"
//            Calendar.WEDNESDAY -> "Wednesday"
//            Calendar.THURSDAY -> "Thursday"
//            Calendar.FRIDAY -> "Friday"
//            Calendar.SATURDAY -> "Saturday"
//            Calendar.SUNDAY -> "Sunday"
//            else -> "Monday"
//        }
//    }


    private fun readJsonFile(day: String): List<TimetableEntry> {
        val json = application.assets.open("entries.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(json)
        val list = mutableListOf<TimetableEntry>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            if (jsonObject.getString("day") == day) {
                val subject = jsonObject.getString("subject")
                val startTime = jsonObject.getString("startTime")
                val endTime = jsonObject.getString("endTime")
                val item = TimetableEntry(subject, startTime, endTime)
                list.add(item)
            }
        }
        return list
    }


    fun refresh(){
        val entries = readJsonFile(dayOfWeek)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = TimetableAdapter(entries)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}



//
//    private fun readJsonFile(): List<TimetableEntry> {
////        val inputStream = resources.openRawResource(R.raw.entries)
////        val json = inputStream.bufferedReader().use { it.readText() }
////        val jsonArray = JSONArray(json)
////        val list = mutableListOf<TimetableEntry>()
////        for (i in 0 until jsonArray.length()) {
////            val jsonObject = jsonArray.getJSONObject(i)
////            val subject = jsonObject.getString("subject")
////            val startTime = jsonObject.getString("startTime")
////            val endTime = jsonObject.getString("endTime")
////            val item = TimetableEntry(subject, startTime, endTime)
////            list.add(item)
////        }
////        return list
//
//        val json = application.assets.open("entries.json").bufferedReader().use { it.readText() }
//        val jsonArray = JSONArray(json)
//        val list = mutableListOf<TimetableEntry>()
//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            val subject = jsonObject.getString("subject")
//            val startTime = jsonObject.getString("startTime")
//            val endTime = jsonObject.getString("endTime")
//            val item = TimetableEntry(subject, startTime, endTime)
//            list.add(item)
//        }
//        return list
//    }
//}

//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import org.json.JSONArray
//
//class TimetableActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.
//        activity_timetable)
//
//        val entries = readJsonFile()
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.adapter = TimetableAdapter(entries)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//
//        // Set up a button to add a new entry
//        val addButton: FloatingActionButton = findViewById(R.id.addButton)
//        addButton.setOnClickListener {
//            // Create an intent to launch the Add/Edit screen
//            val intent = Intent(this, AddEditActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun readJsonFile(): List<TimetableEntry> {
//        val inputStream = resources.openRawResource(R.raw.entries)
//        val json = inputStream.bufferedReader().use { it.readText() }
//        val jsonArray = JSONArray(json)
//        val list = mutableListOf<TimetableEntry>()
//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            val subject = jsonObject.getString("subject")
//            val startTime = jsonObject.getString("startTime")
//            val endTime = jsonObject.getString("endTime")
//            val item = TimetableEntry(subject, startTime, endTime)
//            list.add(item)
//        }
//        return list
//    }
//
//
//}


//class TimetableActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.
//        activity_timetable)
//        val exampleList = generateDummyList(500)
////        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.adapter=TimetableAdapter(exampleList)
//        recyclerView.layoutManager=LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//        // Set up a button to add a new entry
//        val addButton: FloatingActionButton = findViewById(R.id.addButton)
//        addButton.setOnClickListener {
//            // Create an intent to launch the Add/Edit screen
//            val intent = Intent(this, AddEditActivity::class.java)
//            startActivity(intent)
//        }
//    }
//    private fun generateDummyList(size: Int): List<TimetableEntry> {
//
//        val list = ArrayList<TimetableEntry>()
//
//        for (i in 0 until size) {
//
//            val item = TimetableEntry(subject = "Math", startTime = "9:00 AM",
//                endTime = "10:00 AM")
//            list += item
//        }
//
//        return list
//    }
//}
//
