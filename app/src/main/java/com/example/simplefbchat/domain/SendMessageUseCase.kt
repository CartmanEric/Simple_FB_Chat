package com.example.simplefbchat.domain

class SendMessageUseCase(private val repository: FireBaseRepository) {
    fun sendMessage(text: String){
        repository.sendMessage(text)
    }
}