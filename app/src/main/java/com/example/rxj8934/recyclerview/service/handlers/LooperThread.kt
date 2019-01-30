package com.example.rxj8934.recyclerview.service.handlers

import android.os.Handler
import android.os.Looper
import android.os.Message

class LooperThread: Thread() {
    private lateinit var handler:Handler
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
}