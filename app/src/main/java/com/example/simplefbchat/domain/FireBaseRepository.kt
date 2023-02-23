package com.example.simplefbchat.domain

import android.content.Intent
import androidx.lifecycle.LiveData
import com.example.simplefbchat.domain.model.Users

interface FireBaseRepository {
    fun getAuthForIntend(): Intent
    fun putToken(token: String)
    fun checkAuthCondition(): Boolean
    fun getChatMessages(): LiveData<List<Users>>
    fun sendMessage(text: String)
}