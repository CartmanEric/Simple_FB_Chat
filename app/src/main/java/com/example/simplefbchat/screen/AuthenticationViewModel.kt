package com.example.simplefbchat.screen

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplefbchat.repository.firebase.Authentication
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {
    val fbAuth = Authentication()

    fun toGetAuth(token: String) {
        viewModelScope.launch {
            fbAuth.fireBaseAuth(token)
        }
    }

    fun getAuthForIntend(fragmentActivity: FragmentActivity): Intent {
        return fbAuth.getClient(fragmentActivity).signInIntent
    }

    fun checkAuthCondition(): Boolean {
        val checkAuth = fbAuth.auth.currentUser
        if (checkAuth != null) return true
        else return false


    }


}