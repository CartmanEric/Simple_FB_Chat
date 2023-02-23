package com.example.simplefbchat.presentation.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.simplefbchat.data.FirebaseDb
import com.example.simplefbchat.data.RepositoryImpl
import com.example.simplefbchat.domain.GetAllMessagesUseCase
import com.example.simplefbchat.domain.SendMessageUseCase

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val db = FirebaseDb(application)
    private val repo = RepositoryImpl(application,db)
    private val sendMessageUseCase = SendMessageUseCase(repo)


    val allUserMessage = GetAllMessagesUseCase(repo).getChatMessages()

    fun setText(text: String) {
        sendMessageUseCase.sendMessage(text)
    }

}