package com.example.simplefbchat.domain

import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val repository: FireBaseRepository) {
    fun sendMessage(text: String) {
        repository.sendMessage(text)
    }
}