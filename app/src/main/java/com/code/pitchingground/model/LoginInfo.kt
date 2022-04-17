package com.example.pg.model

import android.util.Log

object LoginInfo {
    val users = arrayListOf(Pair("yash","yash"),
        Pair("ankit","ankit"),
        Pair("bhumika","bhumika"),
        Pair("shobhit","shobhit"),
        Pair("akshat","akshat")
    )


    val userInfo = arrayListOf(
        arrayOf("yash","Web Developer","20yo | Guitarist and Musician | Web Developer in the making"),
        arrayOf("ankit","Software Engineer","19yo | Football is Love | Enthusiastic Coder"),
        arrayOf("bhumika", "UI/UX Developer","19yo | Sketching and Designing UIs"),
        arrayOf("shobhit","ML Developer","20yo | Like to develop and train ML models"),
        arrayOf("akshat","Sr Android Developer","20yo | Cricket is my hobby, Android is my passion")
    )

    fun searchUser(name: String, password: String): Boolean {
        Log.d("check","User Working !!!")
        for (user in users) {
            if (user.first == name && user.second == password)
                return true
        }
        return false
    }

    fun getBio(name: String): String {
        for (user in userInfo) {
            if (user[0] == name)
                return user[2]
        }
        return "Unable to fetch Bio!"
    }

    fun addBio(name: String, bioText: String) {
        for (user in userInfo) {
            if (user[0] == name)
                user[2] = bioText
        }
    }

    fun addDesignation(name: String, designation: String) {
        for (user in userInfo) {
            if (user[0] == name)
                user[1] = designation
        }
    }

    fun getDesignation(name: String): String {
        for (user in userInfo) {
            if (user[0] == name)
                return user[1]
        }
        return "Unable to fetch Designation!"
    }
}