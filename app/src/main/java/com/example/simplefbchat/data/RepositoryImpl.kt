package com.example.simplefbchat.data

import android.content.Intent
import androidx.lifecycle.LiveData
import com.example.simplefbchat.domain.FireBaseRepository
import com.example.simplefbchat.domain.model.Users
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fbDB: FirebaseDb,
    private val auth: Authentication
) : FireBaseRepository {

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