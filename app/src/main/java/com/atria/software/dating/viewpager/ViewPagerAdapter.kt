package com.atria.software.dating.viewpager

import android.content.Context
import android.media.Image
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.atria.software.dating.R

class ViewPagerAdapter(
    val context:Context
): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_bg,parent,false)
        return ViewPagerViewHolder(view)
    }

    val array = arrayListOf<Int>(R.drawable.bginit,R.drawable.bgsec,R.drawable.bgthird)
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val imageview = holder.itemView.findViewById<ImageView>(R.id.bgImageView)
        imageview.setImageResource(array[position])
    }

    override fun getItemCount(): Int {
        return 3;
    }
}


