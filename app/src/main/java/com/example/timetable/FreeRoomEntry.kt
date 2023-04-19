package com.example.timetable

data class FreeRoomEntry(
    val roomNo: String,
    val startTime: String,
    val endTime: String = "" // Optional parameter with default value
)

