package com.example.simplefbchat.repository.firebase


import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Authentication() {
    val auth = Firebase.auth
    fun getClient(app: FragmentActivity): GoogleSignInClient {
        val signIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1081290949189-js2rbr38s43gf7v4kbn0v65f8a59lpc9.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(app, signIn)
    }

    fun fireBaseAuth(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)

    }


}