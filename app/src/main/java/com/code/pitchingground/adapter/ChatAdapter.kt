package com.example.pg.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.code.pitchingground.R
import com.example.pg.model.Chat
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ChatAdapter(private var context: Context, private var chatList: ArrayList<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile: CircleImageView = itemView.findViewById(R.id.profileChat)
        val name: TextView = itemView.findViewById(R.id.nameChat)
        val message: TextView = itemView.findViewById(R.id.msgChat)
        val time: TextView = itemView.findViewById(R.id.timeChat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_chat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = chatList[position]
        when (user.imageUrl) {
            "a" -> {
                holder.profile.setImageResource(R.drawable.a)
            }
            "b" -> {
                holder.profile.setImageResource(R.drawable.b)
            }
            "y" -> {
                holder.profile.setImageResource(R.drawable.y)
            }
            "s" -> {
                holder.profile.setImageResource(R.drawable.s)
            }
            else -> {
                Picasso.get().load(user.imageUrl).placeholder(R.drawable.pic).into(holder.profile)
            }
        }
        holder.name.text = user.name.toString()
        holder.message.text = user.message.toString()
        holder.time.text = user.time.toString()

    }

    override fun getItemCount(): Int {
        return chatList.size
    }

}