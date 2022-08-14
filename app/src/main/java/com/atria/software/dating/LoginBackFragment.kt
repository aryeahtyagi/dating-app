package com.atria.software.dating

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atria.software.dating.viewmodel.LoginFragmentViewModel
import com.atria.software.dating.viewmodel.LoginFragmentViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class LoginBackFragment : Fragment() {

    private lateinit var loginFragmentViewModel: LoginFragmentViewModel

    companion object {
        private const val TAG = "LoginBackFragment"
        private val mAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_back, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginFragmentViewModel =
            ViewModelProvider(this, LoginFragmentViewModelFactory(requireContext())).get(
                LoginFragmentViewModel::class.java
            )


        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordText = view.findViewById<EditText>(R.id.passwordEditText)
        val proceedBtn = view.findViewById<ImageButton>(R.id.proceedButton)

        proceedBtn.setOnClickListener {
            if(isValidEmailId(emailEditText.text.toString().trim())){
                if(passwordText.text.toString().contains(" ",true)){
                    passwordText.error = "Spaces Not Allowed"
                }
                else if(passwordText.text.length<7){
                    passwordText.error ="Too Short"
                }
                else if(!passwordText.text.toString().matches(Regex(".*[0-9].*"))){
                    passwordText.error ="Should contain numeric value eg 0,1,2"
                }else{
                    // PERFECT *******************
                    loginFragmentViewModel.signInUser(emailEditText.text.toString(), passwordText.text.toString()){
                        if(it){
                            // we will navigate to confirm email
                            findNavController().navigate(R.id.action_loginBackFragment_to_profileFragment)
                        }else{
                            Log.i(TAG, "onViewCreated: FAILED")

                        }
                    }
                }
            }else{
                emailEditText.error = "Invalid Email"
                emailEditText.performClick()
            }
        }


    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

}