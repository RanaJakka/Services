package com.example.rxj8934.mvvm.router

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.example.rxj8934.mvvm.base_feature.navigation.DeepLinkModel
import com.example.rxj8934.mvvm.base_feature.navigation.DeepLinkProcessor

class MVVMRouter(val appContext:Application): DeepLinkProcessor {
    override val model: DeepLinkModel = DeepLinkModel("https://rana.com/network", "MVVMACtvity")
    override fun matches(deepLink: String):Boolean{
        return deepLink.contains(model.deepLinkUrl)
    }

    override fun execute(deepLinkUri: String) {
        Intent(Intent.ACTION_VIEW).apply {
            data= Uri.parse(model.deepLinkUrl)
            appContext.startActivity(this)
        }
    }
}