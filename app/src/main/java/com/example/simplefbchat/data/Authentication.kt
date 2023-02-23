package com.example.simplefbchat.data


import android.app.Application
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Authentication(private val application: Application) {
    val auth = Firebase.auth
    fun getClient(): GoogleSignInClient {
        val signIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1081290949189-js2rbr38s43gf7v4kbn0v65f8a59lpc9.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(application, signIn)
    }

    fun fireBaseAuth(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)

    }

    fun checkAuthCondition(): Boolean {
        val checkAuth = auth.currentUser
        return checkAuth != null
    }





}