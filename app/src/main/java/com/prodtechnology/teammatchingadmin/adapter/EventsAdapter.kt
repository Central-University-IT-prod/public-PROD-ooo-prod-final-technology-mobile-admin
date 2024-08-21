package com.prodtechnology.teammatchingadmin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.remote.models.Event

class EventsAdapter(
    private var events: MutableList<Event>,
    private val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tv_event_title)
        val tvDeadline: TextView = view.findViewById(R.id.tv_event_deadline)
        val cvRoot: CardView = view.findViewById(R.id.cv_event_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]

        holder.tvTitle.text = event.title
        holder.tvDeadline.text = event.endDate
        holder.cvRoot.setOnClickListener {
            onClickListener.onItemClick(event)
        }
    }

    fun addItem(event: Event){
        events.add(0, event)
        notifyItemInserted(0)
    }

    fun replaceData(newData: MutableList<Event>){
        events = newData
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(event: Event)
    }

}