package com.example.pg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code.pitchingground.R
import com.example.pg.adapter.ChatAdapter
import com.example.pg.adapter.NotificationAdapter
import com.example.pg.model.Chat
import com.example.pg.model.Notification

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var recyclerViewNotification : RecyclerView? = null
    private var notificationList : ArrayList<Notification>? = null
    private var notificationAdapter : NotificationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerViewNotification = layout.findViewById(R.id.rvNotifications)
        notificationList = ArrayList()
        notificationAdapter = NotificationAdapter(requireContext(), notificationList!!)
        recyclerViewNotification?.setHasFixedSize(true)
        recyclerViewNotification?.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerViewNotification?.adapter = notificationAdapter

        notificationList?.add(Notification("4 investors are interested in your pitch!", "8:00 pm"))
        notificationList?.add(Notification("Browse the latest pitches.", "7:30 pm"))
        notificationList?.add(Notification("Ankit scheduled a meet with you.", "6:30 pm"))
        notificationList?.add(Notification("Check out the new problem statements added!!", "5:45 pm"))
        notificationList?.add(Notification("An investor liked your video!!", "5:30 pm"))
        notificationList?.add(Notification("You added a new video.", "4:00 pm"))
        notificationList?.add(Notification("A founder liked your video!!", "2:45 pm"))
        notificationList?.add(Notification("Here's some updates for you.", "Yesterday"))
        notificationList?.add(Notification("Someone viewed your profile.", "Mar 19"))
        notificationList?.add(Notification("Hi there! Welcome to Pitch Ground :)", "Mar 19"))

        return layout

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}