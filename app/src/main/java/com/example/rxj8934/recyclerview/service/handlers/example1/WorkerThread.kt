package com.example.rxj8934.recyclerview.service.handlers.example1

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class WorkerThread:Thread() {
    private val TAG="WorkerThread"
    private val myBoolean = AtomicBoolean(true)
    private val blockOfQueue=ConcurrentLinkedQueue<Runnable>()

   init {
       Log.v(TAG,"THREAD WORKING INIT METHODS")
       start()
   }

    override fun run() {
        while(myBoolean.get()){
            val runnableTask=blockOfQueue.poll()
            runnableTask?.let {
                it.run()
            }

        }
        Log.v(TAG,"Task Terminate Here...!")

    }
    fun exicute(runnable:Runnable):WorkerThread{
        blockOfQueue.add(runnable)
        return this@WorkerThread

    }

    fun quitWorkerThread(){
        myBoolean.set(false)
    }
}