package com.atria.software.dating.viewmodel

import android.content.Context
import android.provider.Settings.System.getString
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.atria.software.dating.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragmentViewModel(
    val context: Context
) : ViewModel() {

    companion object {
        private const val TAG = "LoginFragmentViewModel"
        private val mAuth = Firebase.auth
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    // we will write functions here

    private fun signInRequestBuilder() {
        val signInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, signInOptions)
    }

    fun signIn(f: Fragment) {
        signInRequestBuilder()
        val intent = googleSignInClient.signInIntent
        f.startActivityForResult(intent, 1000)
    }

    // we need sign in with email and password too
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onVerificationSend: (Boolean) -> Unit
    ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    // now we will check if the email is legit or not
                    val user = mAuth.currentUser
                    user?.reload()
                    user?.sendEmailVerification()?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            // email verification is sent
                            onVerificationSend(true)
                        }
                    }
                } else {
                    onVerificationSend(false)
                }
            }
    }

    fun signInUser(
        email: String,
        password: String,
        onVerificationSend: (Boolean) -> Unit
    ) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    // now we will check if the email is legit or not
                    val user = mAuth.currentUser
                    onVerificationSend(true)

                } else {
                    onVerificationSend(false)
                }
            }
    }


}