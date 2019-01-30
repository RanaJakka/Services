package com.example.rxj8934.recyclerview.service.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rxj8934.recyclerview.R
import kotlinx.android.synthetic.main.intent_service_activity.*
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager


class IntentSeriviceActivity: AppCompatActivity() {


    private val responseReceiver=CommunicationBrodCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intent_service_activity)
       // service_input
        requestIntentService.setOnClickListener({
            if(!(service_input.text.toString().equals(""))) {
                sendRequestIntentService(service_input.text.toString())
            }
        })

        val intentFilter = IntentFilter()
        intentFilter.addAction(MyIntentServices.RECEIVER_ACTION)
        registerReceiver(responseReceiver,intentFilter)


    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(responseReceiver)
    }

    private fun sendRequestIntentService(requestData:String){
        val startIntent=Intent(this,MyIntentServices::class.java)
        startIntent.putExtra("RANA",requestData)
        startService(startIntent)

    }


    inner class CommunicationBrodCastReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            val currentTime=intent.getStringExtra("RESP")
            resultRequest.text=currentTime

        }

    }
}