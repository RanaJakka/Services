package com.example.rxj8934.recyclerview.service.services

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer

class MyIntentServices(val name: String="Worker IntentServices") : IntentService(name) {

    private lateinit var chronometer:Chronometer
    companion object {
        val RECEIVER_ACTION = "RESP_ACTION"
    }
    init {

        //chronometer=Chronometer(applicationContext)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(name,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        Log.v(name,"onCreate")
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(name,"onBind")
        return super.onBind(intent)
    }

    override fun setIntentRedelivery(enabled: Boolean) {
        super.setIntentRedelivery(enabled)
    }

    override fun onDestroy() {
        Log.v(name,"onDestroy")
        super.onDestroy()
    }

    override fun onHandleIntent(intent: Intent) {

        Log.v(name,"onHandleIntent")
        val messageFromClient=intent.getStringExtra("RANA")
        sendToClinet("$messageFromClient ${getTimestamp()}")


    }

    private fun sendToClinet(msg:String){
        val intent=Intent()
        intent.action= RECEIVER_ACTION
        intent.putExtra("RESP",msg)
        sendBroadcast(intent)
    }


    fun getTimestamp(): String {
        val elapsedMillis = SystemClock.elapsedRealtime()
        val hours = (elapsedMillis / 3600000).toInt()
        val minutes = (elapsedMillis - hours * 3600000).toInt() / 60000
        val seconds = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong()).toInt() / 1000
        val millis = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong() - (seconds * 1000).toLong()).toInt()
        return "$hours : $minutes :$seconds:$millis"
    }




}