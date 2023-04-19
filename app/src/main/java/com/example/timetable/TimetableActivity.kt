package com.example.timetable
import java.time.LocalTime
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

class TimetableActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
//     Get the current day of the week
        var currentday = getCurrentDay()


    var dayOfWeek : String = currentday


    val currentTime = LocalTime.now()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        // Set up the day of the week at the top
        val btn1=findViewById<Button>(R.id.btn1)
        val btn2=findViewById<Button>(R.id.btn2)
        val btn3=findViewById<Button>(R.id.btn3)
        val btn4=findViewById<Button>(R.id.btn4)
        val btn5=findViewById<Button>(R.id.btn5)
        val btn6=findViewById<Button>(R.id.btn6)
        val btn7=findViewById<Button>(R.id.btn7)

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
        btn6.setOnClickListener{
            dayOfWeek=btn6.text.toString()
            refresh()
        }
        btn7.setOnClickListener{
            dayOfWeek=btn7.text.toString()
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
//        val dayOfWeek = "Wednesday"
//        val currentTime = "10:00 AM - 11:00 AM"

//        println("List of free rooms at $currentTime on $dayOfWeek: $listfreerooms")
        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView2)

        val listFreeRooms = getFreeRooms(dayOfWeek, currentTime).map { FreeRoomEntry(it, "") }
        recyclerView.adapter = FreeRoomAdapter(listFreeRooms)

        recyclerView.adapter = FreeRoomAdapter(listFreeRooms)
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
    private fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        return when (dayOfWeek) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> "Monday"
        }
    }
    private fun readJsonFile(day: String): List<TimetableEntry> {
// Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("timetable_entries", Context.MODE_PRIVATE)

// Iterate over all the entries in the SharedPreferences object
        val list = mutableListOf<TimetableEntry>()
        for (entryKey in sharedPreferences.all.keys) {
            if (entryKey.startsWith("$day-")) {
                val entryJson = sharedPreferences.getString(entryKey, null)
                if (entryJson != null) {
// Deserialize the JSON string into a TimetableEntry object
                    val entry = Gson().fromJson(entryJson, TimetableEntry::class.java)


                    if (entry != null) {

                        list.add(entry)
                    }
                }
            }
        }

// Sort the list by start time

        list.sortBy { it.startTime }

        return list
    }
//    private fun readJsonFile(day: String): List<TimetableEntry> {
//        // Get the SharedPreferences object
//        val sharedPreferences = getSharedPreferences("timetable_entries", Context.MODE_PRIVATE)
//
//        // Iterate over all the entries in the SharedPreferences object
//        val list = mutableListOf<TimetableEntry>()
//        for (entryKey in sharedPreferences.all.keys) {
//            if (entryKey.startsWith("$day-")) {
//                // If the entry key starts with the specified weekday, retrieve the entry data and add it to the list
//                val entryJsonString = sharedPreferences.getString(entryKey, null)
//                if (entryJsonString != null) {
//                    val entryJsonObject = JSONObject(entryJsonString)
//                    val subject = entryJsonObject.getString("subject")
//                    val roomNo= entryJsonObject.getString("roomNo")
//                    val startTime = entryJsonObject.getString("startTime")
//                    val endTime = entryJsonObject.getString("endTime")
//                    val item = TimetableEntry(subject, roomNo, startTime, endTime)
//                    list.add(item)
//                }
//            }
//        }
//        return list
//    }



private fun getFreeRooms(day: String, time: LocalTime): List<String> {
    // Get the SharedPreferences object
    val sharedPreferences = getSharedPreferences("timetable_entries", Context.MODE_PRIVATE)

    // Iterate over all the entries in the SharedPreferences object for the specified weekday
    val usedRooms = mutableListOf<String>()
//    val timeFormat2 = SimpleDateFormat("hh", Locale.getDefault())
//    val date2 = Date()
//    val hour2 = timeFormat2.format(date2)
//
//    val startTimeStr = hour2.toString()
//    val endTimeStr = (hour2.toInt() + 1).toString()

    for (entryKey in sharedPreferences.all.keys) {
        if (entryKey.startsWith("$day-")) {
            // If the entry key starts with the specified weekday, retrieve the entry data and add the room to the usedRooms list
            val entryJsonString = sharedPreferences.getString(entryKey, null)
            if (entryJsonString != null) {
                val entryJsonObject = JSONObject(entryJsonString)
                val roomNo = entryJsonObject.getString("roomNo")
                usedRooms.add(roomNo)
            }
        }
    }

    // Filter out the rooms that are already in use during the specified time
    val timeFormat = SimpleDateFormat("hh", Locale.getDefault())
    val date = Date() // or any other date object
    val hour = timeFormat.format(date)
    val startTime = timeFormat.parse(hour.toString())
    val endTime = timeFormat.parse("$hour+1")
    val freeRooms = mutableListOf<String>()
    for (roomNo in listOf("711", "712", "713", "714", "715", "716", "717", "718", "719", "720")) {
        if (!usedRooms.contains("38-$roomNo")) {
            freeRooms.add("38-$roomNo")
        }

//        else {
//            // Check if the room is free during the specified time
//            val roomEntries = readJsonFile(day).filter { it.roomNo == "38-$roomNo" }
//            var isFree = true
//            for (entry in roomEntries) {
//                val entryStartTime = timeFormat.parse(entry.startTime)
//                val entryEndTime = timeFormat.parse(entry.endTime)
//                if (startTime in entryStartTime..entryEndTime || endTime in entryStartTime..entryEndTime) {
//                    isFree = false
//                    break
//                }
//            }
//            if (isFree) {
//                freeRooms.add("38-$roomNo")
//            }
//        }

    }

    return freeRooms
}



//
//    private fun readJsonFile(day: String): List<TimetableEntry> {
//        val json = application.assets.open("entries.json").bufferedReader().use { it.readText() }
//        val jsonArray = JSONArray(json)
//        val list = mutableListOf<TimetableEntry>()
//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            if (jsonObject.getString("day") == day) {
//                val subject = jsonObject.getString("subject")
//                val startTime = jsonObject.getString("startTime")
//                val endTime = jsonObject.getString("endTime")
//                val item = TimetableEntry(subject, startTime, endTime)
//                list.add(item)
//            }
//        }
//        return list
//    }


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
