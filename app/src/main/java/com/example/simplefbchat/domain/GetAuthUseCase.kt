package com.example.simplefbchat.domain

import android.content.Intent
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(private val repository: FireBaseRepository) {

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