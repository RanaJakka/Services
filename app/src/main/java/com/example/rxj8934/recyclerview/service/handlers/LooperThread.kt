package com.example.rxj8934.recyclerview.service.handlers

import android.os.Handler
import android.os.Looper
import android.os.Message

/*
This the class which is similar to the main thread. In Android Main thread contains(Looper,MessageQueue).
Same as Main Thread, I created Thread and it's Looper, and Message Queue. In Our example1 We cared concurrentLinkedList(MessageQueue).
But here, we don't need to create like in that example,when ever (Looper.prepare()) gets called, automatically, that thread contains
both looper and as well as message queue. So now this Thread contains Message queue,so message queue contains message objects or Runnable objects.
We need to handled those message/runnable tasks. So to handled those messages , we need to create handler object to that thread. which as follows...
So loppers is responsible to deliver messages to that corsponding handlers object. and these handler will be executed on that corsponding therad.
* */




class LooperThread() : Thread() {
    private lateinit var handler: Handler// handler object associated with thread.
    override fun run() {
        Looper.prepare()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
        Looper.loop()
        super.run()
    }

    /* FYI

    // From handler constructor
mLooper = Looper.myLooper();
if (mLooper == null) {
    throw new RuntimeException(
        "Can't create handler inside thread that has not called Looper.prepare()");
}
    *
    *
    *
    * */
}