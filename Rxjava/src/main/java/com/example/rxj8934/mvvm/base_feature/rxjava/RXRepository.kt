package com.example.rxj8934.mvvm.base_feature.rxjava

import io.reactivex.Observable

class RXRepository {
    fun getJustObservable()=Observable.just("RANA","PRATHAP","JAKKA").map {
        if(it.length>5)
            it
        else
            it
    }

}