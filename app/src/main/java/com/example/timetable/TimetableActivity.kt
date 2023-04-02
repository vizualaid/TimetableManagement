

//package com.example.timetable
//
package com.example.timetable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class TimetableActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val entries = readJsonFile()
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

    private fun readJsonFile(): List<TimetableEntry> {
        val inputStream = resources.openRawResource(R.raw.entries)
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(json)
        val list = mutableListOf<TimetableEntry>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val subject = jsonObject.getString("subject")
            val startTime = jsonObject.getString("startTime")
            val endTime = jsonObject.getString("endTime")
            val item = TimetableEntry(subject, startTime, endTime)
            list.add(item)
        }
        return list
    }
}

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
