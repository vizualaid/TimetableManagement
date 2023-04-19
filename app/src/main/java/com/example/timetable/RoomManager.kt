package com.example.timetable
import java.text.SimpleDateFormat
import java.util.*
class RoomManager {
        data class Room(val number: String, val startTime: Date, val endTime: Date)

        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        // List of all rooms
        private val rooms = listOf(
            Room("38-711", dateFormat.parse("2023-04-19 10:00"), dateFormat.parse("2023-04-19 11:00")),
            Room("38-712", dateFormat.parse("2023-04-19 11:00"), dateFormat.parse("2023-04-19 12:00")),
            Room("38-713", dateFormat.parse("2023-04-19 13:00"), dateFormat.parse("2023-04-19 14:00")),
            Room("38-714", dateFormat.parse("2023-04-19 14:00"), dateFormat.parse("2023-04-19 15:00")),
            Room("38-715", dateFormat.parse("2023-04-19 15:00"), dateFormat.parse("2023-04-19 16:00")),
            Room("38-716", dateFormat.parse("2023-04-19 16:00"), dateFormat.parse("2023-04-19 17:00"))
        )

        /**
         * Get the number of free rooms for a given time slot
         * @param startTime The start time of the time slot in the format "yyyy-MM-dd HH:mm"
         * @param endTime The end time of the time slot in the format "yyyy-MM-dd HH:mm"
         * @return The number of free rooms
         */
        fun getFreeRooms(startTime: String, endTime: String): Int {
            val start = dateFormat.parse(startTime) ?: return -1 // Return -1 if invalid start time
            val end = dateFormat.parse(endTime) ?: return -1 // Return -1 if invalid end time
            var count = 0
            for (room in rooms) {
                if ((start >= room.endTime) || (end <= room.startTime)) { // Check if the room is free
                    count++
                }
            }
            return count
        }
    }
