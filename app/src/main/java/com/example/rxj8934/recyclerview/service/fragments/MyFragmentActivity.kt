package com.example.rxj8934.recyclerview.service.fragments

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import com.example.rxj8934.R
import kotlinx.android.synthetic.main.myfragment_activity.*

class MyFragmentActivity: FragmentActivity() {
    private val TAG="MyFragmentActivity -->"
    private lateinit var fragmentManager:FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myfragment_activity)
        fragmentManager=supportFragmentManager
        addFragment.setOnClickListener {
            addFragments()
        }
        fragmentManager.addOnBackStackChangedListener{
            Log.i(TAG,"-->${fragmentManager.backStackEntryCount}")
            fragmentCount.text="${fragmentManager.backStackEntryCount}"
        }




    }


    private fun addFragments(){
        val fragmentTest=FragmentTest()
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container,fragmentTest,FragmentTest.TAG)
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragmnet=fragmentManager.findFragmentById(R.id.fragment_container)
        fragmnet?.apply {
            val fragmentTransaction=this@MyFragmentActivity.fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragmnet)
            fragmentTransaction.commit()
            return
        }
        super.onBackPressed()

        val text="Rana".toUpperCase()
    }
}
/*
Note Points:
1. Once Fragment transaction is commit You cant use that again.
2. Every fragment backstack is maintain by the Fragment Manager.
3. you can check the fragment back stack count by this addOnBackStackChangedListener()
4.you can add any no.of fragmnets to the framelayout.
5. fragment replace() is just replace the current fragment from the Framelayout with new fragment.
        5.1. there some interesting secnario with addtoBackStack + replace . observe back stack count.
        5.2. popBackStack()
6. Fragments are Modular components of Andorid.
7. Fragments should not aware of each other.
8. Fragments should not launch other fragments, So Activity should aware of all fragments with in that.
*
*
*
*
* */