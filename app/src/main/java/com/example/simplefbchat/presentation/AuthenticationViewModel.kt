package com.example.simplefbchat.presentation

import androidx.lifecycle.ViewModel
import com.example.simplefbchat.domain.GetAuthUseCase
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val repository: GetAuthUseCase
) : ViewModel() {
    val checkAuthCondition = repository.checkAuthCondition()
    val getAuthIntend = repository.getAuthForIntend()

    fun putToken(token: String) {
        repository.putToken(token)
    }


}