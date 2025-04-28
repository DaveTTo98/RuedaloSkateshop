package com.ddeveloper.ruedaloskateshop.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ddeveloper.ruedaloskateshop.R

class Profile_Fragment:  Fragment() {

    //INIT VARIABLES
private lateinit var showName: TextView

    //SHARED PREFERENCES
private lateinit var sharedPreferences: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)



    }
}