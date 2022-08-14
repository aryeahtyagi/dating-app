package com.atria.software.dating

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.atria.software.dating.viewpager.ViewPagerAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class StartFragment : Fragment() {

    companion object{
        private const val TAG = "StartFragment"
        val mAuth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(mAuth.currentUser!=null){
            findNavController().navigate(R.id.action_startFragment_to_profileFragment)
        }
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager)
        val dotView = view.findViewById<ImageView>(R.id.dotView)
        viewpager.adapter = ViewPagerAdapter(requireContext())

        val timer = Handler().postDelayed({
            object : CountDownTimer(50_000, 4000) {
                override fun onTick(p0: Long) {
                    if (viewpager.currentItem == 2) {
                        viewpager.setCurrentItem(0, true)
                    } else {
                        viewpager.setCurrentItem(viewpager.currentItem.plus(1), true)
                    }
                }

                override fun onFinish() {

                }

            }.start()
        },4000);


        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i(TAG, "onPageSelected: ")
                if(position == 0){
                    dotView.setImageResource(R.drawable.ic_dots)
                }
                if (position == 1){
                    dotView.setImageResource(R.drawable.ic_midselect)
                }
                if(position == 2){
                    dotView.setImageResource(R.drawable.ic_lastselect)
                }
            }
        })
    }

}