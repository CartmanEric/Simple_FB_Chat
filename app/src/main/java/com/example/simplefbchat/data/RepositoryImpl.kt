package com.example.simplefbchat.data

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData
import com.example.simplefbchat.domain.FireBaseRepository
import com.example.simplefbchat.domain.model.Users

class RepositoryImpl(private val application: Application, private val fbDB: FirebaseDb):FireBaseRepository {
    private val auth = Authentication(application)
    override fun getAuthForIntend(): Intent {
        return auth.getClient().signInIntent
    }

    override fun putToken(token: String) {
        auth.fireBaseAuth(token)
    }

    override fun checkAuthCondition(): Boolean {
        return auth.checkAuthCondition()
    }

    override fun getChatMessages(): LiveData<List<Users>> {
        return fbDB.getAllUserMessage()
    }

    override fun sendMessage(text: String) {
        fbDB.sendText(text)
    }
}