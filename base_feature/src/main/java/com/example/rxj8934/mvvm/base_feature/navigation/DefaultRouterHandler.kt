package com.example.rxj8934.mvvm.base_feature.navigation

class DefaultRouterHandler(val deepLinkProcessor:Set<DeepLinkProcessor>): RouteHandler {
    override fun executeProcess(deeperLink: String): Boolean {
        deepLinkProcessor.forEach{
            if(it.matches(deeperLink)){
                it.execute(deeperLink)
                return true
            }
        }
        return false
    }
}