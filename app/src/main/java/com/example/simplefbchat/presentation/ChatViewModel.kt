package com.example.simplefbchat.presentation

import androidx.lifecycle.ViewModel
import com.example.simplefbchat.domain.GetAllMessagesUseCase
import com.example.simplefbchat.domain.SendMessageUseCase
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getMessageUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {
    val allUserMessage = getMessageUseCase.getChatMessages()

    fun setText(text: String) {
        sendMessageUseCase.sendMessage(text)
    }

}