package com.example.rxj8934.mvvm.base_feature.navigation

open interface RouteHandler {
    fun executeProcess(deeperLink:String):Boolean
}


open interface DeepLinkProcessor{
    val model: DeepLinkModel
    fun matches(deepLink:String):Boolean
    fun execute(deepLinkUri:String)
}

data class DeepLinkModel(val deepLinkUrl:String, val conceptName:String)