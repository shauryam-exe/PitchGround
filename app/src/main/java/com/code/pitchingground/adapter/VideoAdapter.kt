package com.example.pg.adapter

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.code.pitchingground.R
import com.example.pg.model.VideoItem
import kotlin.random.Random


class VideoAdapter(val videoItems: ArrayList<VideoItem>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoData(videoItems[position], position)
    }

    override fun getItemCount(): Int {
        return videoItems.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoView = itemView.findViewById<VideoView>(R.id.videoView)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.videoProgressBar)
        val userNameTextView = itemView.findViewById<TextView>(R.id.nameOnVideoTextView)
        val ideaNameTextView = itemView.findViewById<TextView>(R.id.ideaNameVideoTextView)
        val askTextView = itemView.findViewById<TextView>(R.id.askOnVideoTextView)
        val likeTextView = itemView.findViewById<TextView>(R.id.likeTextView)
        val likeButton = itemView.findViewById<ToggleButton>(R.id.likeButton)


        fun setVideoData(videoData: VideoItem, position: Int) {

            userNameTextView.text = videoData.userName
            if (position==1 || position==4) {
                userNameTextView.setBackgroundResource(R.drawable.video_name_verified)
            }
            if (position==2 || position==3 || position==5) {
                userNameTextView.setBackgroundResource(R.drawable.video_student_verify)
            }
            ideaNameTextView.text = videoData.ideaName
            askTextView.text = "Ask : " + videoData.ask
            var likes = (3..15).random()
            likeTextView.text = likes.toString()

            likeButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    likes++
                    likeTextView.text = likes.toString()
                } else {
                    likes--
                    likeTextView.text = likes.toString()
                }
            }

                videoView.setVideoPath(videoData.videoURL)

                videoView.setOnPreparedListener() {
                    progressBar.visibility = View.GONE
                    it.start()

                    val videoRatio: Float = it.videoWidth / it.videoHeight.toFloat()
                    val screenRatio: Float = videoView.width / videoView.height.toFloat()

                    val scale: Float = screenRatio / videoRatio
                    if (scale >= 1) {
                        videoView.scaleX = scale
                    } else {
                        videoView.scaleY = 1 / scale
                    }

                }
                videoView.setOnCompletionListener {
                    it.start()
                }
            }

        }
    }
