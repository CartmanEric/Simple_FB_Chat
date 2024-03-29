package com.example.simplefbchat.domain

import androidx.lifecycle.LiveData
import com.example.simplefbchat.domain.model.Users
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(private val repository: FireBaseRepository) {

    fun getChatMessages(): LiveData<List<Users>> =
        repository.getChatMessages()
}