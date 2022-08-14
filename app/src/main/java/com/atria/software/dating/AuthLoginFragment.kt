package com.atria.software.dating

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atria.software.dating.viewmodel.LoginFragmentViewModel
import com.atria.software.dating.viewmodel.LoginFragmentViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthLoginFragment : Fragment() {


    private lateinit var loginFragmentViewModel: LoginFragmentViewModel

    companion object {
        private const val TAG = "AuthLoginFragment"
        private val mAuth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginFragmentViewModel =
            ViewModelProvider(this, LoginFragmentViewModelFactory(requireContext())).get(
                LoginFragmentViewModel::class.java
            )

        val googleBtn = view.findViewById<ImageButton>(R.id.googleButton)
        googleBtn.setOnClickListener {
            loginFragmentViewModel.signIn(this)
        }

        val emailBtn = view.findViewById<ImageButton>(R.id.emailButton)
        emailBtn.setOnClickListener {
            this.parentFragment?.parentFragment?.findNavController()?.navigate(R.id.action_startFragment_to_loginBackFragment)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1000 -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val result = task.getResult(ApiException::class.java)
                    Log.i(TAG, "onActivityResult: ${result.id}")
                    firebaseAuthWithGoogle(result.idToken)
                } catch (e: ApiException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Something went wrong ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(token: String?) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    this.parentFragment?.parentFragment?.findNavController()?.navigate(R.id.action_startFragment_to_profileFragment)
                    Toast.makeText(context, "SUCCESS SIGN IN", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Something went wrong 60", Toast.LENGTH_SHORT).show()
                }
            }
    }

}