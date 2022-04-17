package com.example.pg.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.code.pitchingground.R
import com.example.pg.model.Notification

class NotificationAdapter(
    private var context: Context,
    private var notificationList: ArrayList<Notification>
) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val message: TextView = itemView.findViewById(R.id.msgNotification)
        val time: TextView = itemView.findViewById(R.id.timeNotification)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_notification_tem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = notificationList[position]
        holder.message.text = user.notification.toString()
        holder.time.text = user.timeN.toString()

    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

}
