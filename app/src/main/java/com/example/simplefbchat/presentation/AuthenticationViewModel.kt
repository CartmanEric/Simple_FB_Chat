package com.example.simplefbchat.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.simplefbchat.data.FirebaseDb
import com.example.simplefbchat.data.RepositoryImpl
import com.example.simplefbchat.domain.GetAuthUseCase

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
private val db = FirebaseDb(application)
    private val repositoryImpl = RepositoryImpl(application,db)
    private val repository = GetAuthUseCase(repositoryImpl)

     val checkAuthCondition = repository.checkAuthCondition()
    val  getAuthIntend = repository.getAuthForIntend()


    fun putToken(token: String){
        repository.putToken(token)
    }


}