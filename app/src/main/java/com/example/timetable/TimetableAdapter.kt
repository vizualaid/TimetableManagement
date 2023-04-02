package com.example.timetable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//https://www.youtube.com/watch?v=afl_i6uvvU0&t=17s
//class TimetableAdapter(private var : List<TimetableEntry>) : RecyclerView.Adapter<TimetableAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val entry = entries[position]
//        holder.bind(entry)
//    }
//
//    override fun getItemCount() = entries.size
//
//    fun updateEntries(entries: List<TimetableEntry>) {
//        this.entries = entries
//        notifyDataSetChanged()
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val subjectView: TextView = itemView.findViewById(R.id.subject)
//        private val timeView: TextView = itemView.findViewById(R.id.time)
//
//        fun bind(entry: TimetableEntry) {
//            subjectView.text = entry.subject
//            timeView.text = "${entry.startTime} - ${entry.endTime}"
//        }
//    }
//}
class TimetableAdapter(private val timetableData: List<TimetableEntry>):
//    : List<TimetableData>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {


        //this is called only few times
        // ..1 time for each item that fits onto the screen
        // +  a few more when we scrolled
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
        return TimetableViewHolder(itemView)
    }
        // is called over and over again either when we scroll
        // or an item that comes to the screen has to be filled with data
        // or when we have to update the item with new data
    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        val currentItem = timetableData[position]
        holder.subjectView.text = (currentItem.subject)
        val timehere="${currentItem.startTime} - ${currentItem.endTime}"
        holder.timeView.text = timehere

//            if(position==0)
//            {
//                holder.subjectView.setBackgroundColor(Color.GRAY)
//            }
    }

    override fun getItemCount()= timetableData.size

    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectView: TextView = itemView.findViewById(R.id.subject)
        val timeView: TextView = itemView.findViewById(R.id.time)

    }


}

