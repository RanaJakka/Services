package com.example.rxj8934.recyclerview.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.rxj8934.recyclerview.R

class MainActivity: AppCompatActivity() {
    private val boundserviceConnection:BoundServiceConnection
    private lateinit var updateTextView:TextView
    init {
        boundserviceConnection=BoundServiceConnection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateTextView=findViewById(R.id.timerService)
        updateTextView.setOnClickListener(View.OnClickListener {
            boundserviceConnection.boundServices()?.let {
                updateTextView.text=it.getTimestamp()
            }

        })


    }

    override fun onStart() {
        super.onStart()
        val serviceIntent= Intent(this@MainActivity,LongRunningService::class.java)
        bindService(serviceIntent,boundserviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        Log.v("LongRunningService","ONSTOP: ${boundserviceConnection.isServiceConnected()}")
        if(boundserviceConnection.isServiceConnected())
        unbindService(boundserviceConnection)
    }
    inner class BoundServiceConnection:ServiceConnection{
        private var isConnected=false
        private var longRunningService:LongRunningService?=null
        override fun onServiceDisconnected(name: ComponentName?) {
            isConnected=false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isConnected=true
            longRunningService= ((service!! as LongRunningService.MyBinder).getServiceInstance())
        }

        fun isServiceConnected()=isConnected
        fun boundServices()=longRunningService

    }

}