package com.example.timetable

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class AddEditActivity : AppCompatActivity() {
    private lateinit var subjectEditText: EditText
    private lateinit var startTimeEditText: EditText
    private lateinit var endTimeEditText: EditText
    private lateinit var weekSpinner: Spinner

    private lateinit var STimeText: EditText
    private lateinit var ETimeText: EditText
    private lateinit var btnSTimePicker: Button
    private lateinit var btnETimePicker: Button
    // Declare variables to hold selected date and time
//    private var mYear: Int = 0
//    private var mMonth: Int = 0
//    private var mDay: Int = 0
    private var mMinute: Int = 0
    private var mHour: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        // Initialize UI components
        btnSTimePicker = findViewById(R.id.setstart)
        btnETimePicker= findViewById(R.id.setend)
        STimeText = findViewById(R.id.startTimeEditText)
        ETimeText = findViewById(R.id.endTimeEditText)


        btnSTimePicker.setOnClickListener {
            val c = Calendar.getInstance()
            mHour = c.get(Calendar.HOUR_OF_DAY)
            mMinute = c.get(Calendar.MINUTE)

            // Create a TimePickerDialog and display it
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // Format the selected time as a string and display it in the TimeText EditText
                    var hourOfDay = hourOfDay
                    val AM_PM = if (hourOfDay < 12) "AM" else "PM"
                    if (AM_PM == "PM") hourOfDay -= 12
                    if (hourOfDay == 0) hourOfDay += 12
                    if (minute < 10) {
                        STimeText.setText("$hourOfDay:0$minute $AM_PM")
                    } else {
                        STimeText.setText("$hourOfDay:$minute $AM_PM")
                    }
                },
                mHour, mMinute, false
            )
            timePickerDialog.show()
        }

        btnETimePicker.setOnClickListener {
            val c = Calendar.getInstance()
            mHour = c.get(Calendar.HOUR_OF_DAY)
            mMinute = c.get(Calendar.MINUTE)

            // Create a TimePickerDialog and display it
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // Format the selected time as a string and display it in the TimeText EditText
                    var hourOfDay = hourOfDay
                    val AM_PM = if (hourOfDay < 12) "AM" else "PM"
                    if (AM_PM == "PM") hourOfDay -= 12
                    if (hourOfDay == 0) hourOfDay += 12
                    if (minute < 10) {
                        ETimeText.setText("$hourOfDay:0$minute $AM_PM")
                    } else {
                        ETimeText.setText("$hourOfDay:$minute $AM_PM")
                    }
                },
                mHour, mMinute, false
            )
            timePickerDialog.show()
        }

        // Set up the toolbar
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setActionBar(toolbar)

        // Show the back button
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the UI elements
        subjectEditText = findViewById(R.id.subjectEditText)
        startTimeEditText = findViewById(R.id.startTimeEditText)
        endTimeEditText = findViewById(R.id.endTimeEditText)
        // Initialize the weekSpinner variable with the spinner view from your layout file
        weekSpinner = findViewById(R.id.weekSpinner)

        // Populate the spinner with the days of the week
        val daysOfWeek =
            arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysOfWeek)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weekSpinner.adapter = adapter

        // Set up the save button
        val saveButton: Button = findViewById(R.id.action_save)
        saveButton.setOnClickListener {
            saveEntry()
        }
    }
    private fun saveEntry() {
        // Get the entered data from the UI elements
        val subject = subjectEditText.text.toString().trim()
        val startTime = startTimeEditText.text.toString().trim()
        val endTime = endTimeEditText.text.toString().trim()
        val week = weekSpinner.selectedItem.toString()

        // Validate the data
        if (subject.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("timetable_entries", Context.MODE_PRIVATE)

        // Add the entry data as a key-value pair in the SharedPreferences object
        val entryJsonString = "{\"subject\":\"$subject\",\"startTime\":\"$startTime\",\"endTime\":\"$endTime\"}"
//
        val entryKey = "$week-${System.currentTimeMillis()}"
        sharedPreferences.edit().putString(entryKey, entryJsonString).apply()

        // Close the activity and return to the previous screen
        setResult(Activity.RESULT_OK)
        finish()
    }


//
//    private fun saveEntry() {
//        // Get the entered data from the UI elements
//        val subject = subjectEditText.text.toString().trim()
//        val startTime = startTimeEditText.text.toString().trim()
//        val endTime = endTimeEditText.text.toString().trim()
//        val week = weekSpinner.selectedItem.toString()
//
//        // Validate the data
//        if (subject.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
//            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        // Create a JSON object with the entry data
//        val jsonObject = JSONObject()
//        jsonObject.put("subject", subject)
//        jsonObject.put("startTime", startTime)
//        jsonObject.put("endTime", endTime)
//        jsonObject.put("day", week)
//
//        // Write the JSON object to a file
//        val fileName = "entries.json"
//        val file = File(filesDir, fileName)
//        val fileWriter = FileWriter(file, true).also {
//            it.write(jsonObject.toString() + "\n")
//            it.close()
//        }
//
//        // Close the activity and return to the previous screen
//        setResult(Activity.RESULT_OK)
//        finish()
//    }
}