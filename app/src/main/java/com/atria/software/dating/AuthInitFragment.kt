package com.atria.software.dating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class AuthInitFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_init, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signUpButton = view.findViewById<ImageButton>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_authInitFragment_to_authModeFragment)
        }

        val loginButton = view.findViewById<ImageButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_authInitFragment_to_authLoginFragment)
        }

    }

}