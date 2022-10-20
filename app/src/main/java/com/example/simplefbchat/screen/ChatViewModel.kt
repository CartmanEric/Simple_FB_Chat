package com.example.simplefbchat.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplefbchat.model.Users
import com.example.simplefbchat.repository.firebase.Authentication
import com.example.simplefbchat.repository.firebase.Db
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatViewModel : ViewModel() {
    val repo = Db()
    val authFB = Authentication()
    val userData: MutableLiveData<List<Users>> by lazy {
        MutableLiveData<List<Users>>()
    }

    fun setText(text: String) {
        repo.sendText(Users(authFB.auth.currentUser?.displayName, text))
    }


    fun getFBResult() {
        repo.myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = arrayListOf<Users>()
                for (s in snapshot.children) {
                    val user = s.getValue(Users::class.java)
                    if (user != null) {
                        userList.add(user)
                        userData.value = userList
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}