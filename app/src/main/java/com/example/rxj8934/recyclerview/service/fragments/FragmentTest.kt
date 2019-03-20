package com.example.rxj8934.recyclerview.service.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxj8934.R

class FragmentTest:Fragment() {
    companion object {
        val TAG="FragmentTest"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test,container,false)
    }
}