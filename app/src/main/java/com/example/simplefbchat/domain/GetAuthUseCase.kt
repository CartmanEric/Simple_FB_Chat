package com.example.simplefbchat.domain

import android.content.Intent

class GetAuthUseCase(private val repository: FireBaseRepository) {

    fun getAuthForIntend(): Intent {
        return repository.getAuthForIntend()
    }

    fun putToken(token: String) {
        repository.putToken(token)
    }

    fun checkAuthCondition(): Boolean {
        return repository.checkAuthCondition()
    }
}