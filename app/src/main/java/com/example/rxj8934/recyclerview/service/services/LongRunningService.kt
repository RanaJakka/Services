package com.example.rxj8934.recyclerview.service.services

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer

class LongRunningService: Service() {
    private val LOG_TAG="LongRunningService"
    private lateinit var chronometer:Chronometer
    init {

    }
    private val interactionBinder=MyBinder()
    override fun onCreate() {
        chronometer=Chronometer(this)
        chronometer.base=SystemClock.elapsedRealtime()
        chronometer.start()
        Log.v(LOG_TAG, "in ON CREATE");
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.v(LOG_TAG, "in onBIND");
            Log.v(LOG_TAG,"LONG RUNNING OPERATION");

        return interactionBinder

    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.v(LOG_TAG, "in onReBIND");
    }

    override fun unbindService(conn: ServiceConnection) {
        Log.v(LOG_TAG, "in UNBIND");
        super.unbindService(conn)
    }

    override fun onDestroy() {
        Log.v(LOG_TAG, "in DESTROY");
        super.onDestroy()
    }

    fun getTimestamp(): String {
        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
        val hours = (elapsedMillis / 3600000).toInt()
        val minutes = (elapsedMillis - hours * 3600000).toInt() / 60000
        val seconds = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong()).toInt() / 1000
        val millis = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong() - (seconds * 1000).toLong()).toInt()
        return "$hours : $minutes :$seconds:$millis"
    }


    inner class MyBinder: Binder() {
        public fun  getServiceInstance()=this@LongRunningService
    }

}