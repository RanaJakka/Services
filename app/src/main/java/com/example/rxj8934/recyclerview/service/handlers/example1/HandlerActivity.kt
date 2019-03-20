package com.example.rxj8934.recyclerview.service.handlers.example1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.example.rxj8934.R
import kotlinx.android.synthetic.main.activity_handler2.*

/*
this example is blue print of handler, looper, HandlerThread execution.
To understand how the HandlerThread works.
To understand main thread Looper, task queue.
*/

class HandlerActivity:AppCompatActivity() {
    private lateinit var instanceOfWorkerThread: WorkerThread
    private val mainThreadHandler= object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            text_handler.text=msg.obj.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler2)
        instanceOfWorkerThread= WorkerThread()
        instanceOfWorkerThread.exicute(object :Runnable{
            override fun run() {
                Thread.sleep(1000)
                val message=Message.obtain()
                message.obj="Task 1 Completed"
                mainThreadHandler.sendMessage(message)
            }

        }).exicute(object :Runnable{
            override fun run() {
                Thread.sleep(5000)
                val message=Message.obtain()
                message.obj="Task 2 Completed"
                mainThreadHandler.sendMessage(message)
            }

        }).exicute(object :Runnable{
            override fun run() {
                Thread.sleep(6000)
                val message=Message.obtain()
                message.obj="Task 3 Completed"
                mainThreadHandler.sendMessage(message)
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        instanceOfWorkerThread.quitWorkerThread()
    }
}