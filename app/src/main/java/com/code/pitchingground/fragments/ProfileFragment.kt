package com.example.pg.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.code.pitchingground.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var name: TextView
    lateinit var addBioFAB: FloatingActionButton
    lateinit var bioTextView: TextView
    lateinit var desigantionTextView: TextView
    lateinit var auth: FirebaseAuth
    lateinit var fstore: FirebaseFirestore

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.nameProfile)
        desigantionTextView = view.findViewById(R.id.designationTextView)
        bioTextView = view.findViewById(R.id.bioTextView)
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        val userId = auth.currentUser!!.uid
        val document = fstore.collection("users").document(userId)
        document.get().addOnSuccessListener { document ->
            if (document != null) {
                name.text = document.data?.get("name").toString()
                bioTextView.text = document.data?.get("bio").toString()
                desigantionTextView.text = document.data?.get("designation").toString()
            }
        }
            .addOnFailureListener {
                Log.d("Checking Firebase", "Unable to fetch user data")
            }



        addBioFAB = view.findViewById(R.id.addBioFAB)
        addBioFAB.setOnClickListener {
            showBioDialog()
        }
    }

    fun showBioDialog() {
        val userId = auth.currentUser!!.uid
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle("Add Bio")
        val input = EditText(activity!!)
        input.inputType = InputType.TYPE_CLASS_TEXT
        dialog.setView(input)
        dialog.setPositiveButton("Add") { dialog, _->
            val bio = input.text.toString()
            val documentReference = fstore.collection("users").document(userId)
            var addBio = HashMap<String, String>()
            addBio.put("bio",bio)
            documentReference.set(addBio)
            bioTextView.text = bio
            dialog.dismiss()
            showDesignationDialog()
        }
        dialog.show()
    }

    private fun showDesignationDialog() {
        val userId = auth.currentUser!!.uid
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle("Add Designation")
        val input = EditText(activity!!)
        input.inputType = InputType.TYPE_CLASS_TEXT
        dialog.setView(input)
        dialog.setPositiveButton("Add") { dialog, _->
            val designation = input.text.toString()
            val documentReference = fstore.collection("users").document(userId)
            var addDesignation = HashMap<String, String>()
            addDesignation.put("designation",designation)
            documentReference.set(addDesignation)
            desigantionTextView.text = designation
            dialog.dismiss()
        }
        dialog.show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}