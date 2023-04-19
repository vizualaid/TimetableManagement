package com.example.timetable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FreeRoomAdapter(private val freeRoomData: List<FreeRoomEntry>) :
    RecyclerView.Adapter<FreeRoomAdapter.TimetableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.freeroom_item, parent, false)
        return TimetableViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        val currentItem = freeRoomData[position]
        holder.roomNoView.text = currentItem.roomNo
//        val timehere = "${currentItem.startTime} - ${currentItem.endTime}"
//        holder.timeView.text = timehere
    }

    override fun getItemCount() = freeRoomData.size

    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNoView: TextView = itemView.findViewById(R.id.roomNoTextView)
//        val timeView: TextView = itemView.findViewById(R.id.timeTextView)
    }
}
