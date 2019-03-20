package com.example.rxj8934.mvvm.base_feature.rxjava

import io.reactivex.Observer
import io.reactivex.functions.Action


class RxPresenter {
    val rxRepository:RXRepository
    init {
        rxRepository= RXRepository()

    }
    fun makeSubscribeToJustObservable(observer: Observer<String>){
        rxRepository.getJustObservable().subscribe(observer)
    }



}