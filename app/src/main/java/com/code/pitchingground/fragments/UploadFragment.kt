package com.example.pg.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.code.pitchingground.R
import com.example.pg.adapter.VideoAdapter
import com.example.pg.model.User
import com.example.pg.model.VideoItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UploadFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var uploadButton: Button
    lateinit var videoPickButton: Button
    lateinit var ideaNameTextView: TextView
    lateinit var ideaDescriptionTextView: TextView
    lateinit var uploadVideoView: VideoView
    lateinit var askTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoPickButton = view.findViewById(R.id.videoSelectButton)
        ideaNameTextView = view.findViewById(R.id.ideaNameTextView)
        ideaDescriptionTextView = view.findViewById(R.id.ideaDescriptionTextView)
        uploadVideoView = view.findViewById(R.id.uploadVideoView)
        askTextView = view.findViewById(R.id.askUploadTextView)

        val mediaController = MediaController(activity)
        uploadVideoView.setMediaController(mediaController)


        videoPickButton.setOnClickListener {
            videoPickDialog()
        }

    }

    private fun videoPickDialog() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val videoUri = data?.data
            val ideaName = ideaNameTextView.text.toString()
            val ask = askTextView.text.toString()

            uploadVideoView.visibility = View.VISIBLE
            uploadVideoView.setVideoURI(videoUri)
            uploadVideoView.start()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UploadFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UploadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}