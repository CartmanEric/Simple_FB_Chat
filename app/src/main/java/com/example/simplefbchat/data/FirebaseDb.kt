package com.example.simplefbchat.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplefbchat.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseDb @Inject constructor(
    private val authentication: Authentication
) {


    private val database = Firebase.database
    private val getAllMessage = MutableLiveData<List<Users>>()
    private val myRef = database.getReference("message")

    fun sendText(userText: String) {
        val currentUserName = authentication.auth.currentUser?.displayName
        val userMessage = Users(currentUserName, userText)
        myRef.child(myRef.push().key ?: "anything").setValue(userMessage)
    }


    fun getAllUserMessage(): LiveData<List<Users>> {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = arrayListOf<Users>()
                for (s in snapshot.children) {
                    val user = s.getValue(Users::class.java)
                    if (user != null) {
                        userList.add(user)
                        getAllMessage.value = userList
                        Log.d("test", "$userList")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return getAllMessage
    }


}