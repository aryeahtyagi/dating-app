package com.atria.software.dating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class name : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameText = view.findViewById<EditText>(R.id.nameText)

        // now we need to upload these details to the firebase
    }

    fun uploadNameToFirestore(){
        val firestore = FirebaseFirestore.getInstance()

    }

}