package com.example.simplefbchat.repository.firebase


import com.example.simplefbchat.model.Users
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Db {
    val database = Firebase.database
    val myRef = database.getReference("message")

    fun sendText(userText: Users){
        myRef.child(myRef.push().key?: "anything").setValue(userText)
    }



}