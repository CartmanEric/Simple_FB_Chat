package com.example.simplefbchat.domain

import androidx.lifecycle.LiveData
import com.example.simplefbchat.domain.model.Users

class GetAllMessagesUseCase(private val repository: FireBaseRepository) {

    fun getChatMessages():LiveData<List<Users>> =
        repository.getChatMessages()
}