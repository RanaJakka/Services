package com.example.rxj8934.mvvm.base_feature.rxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxJavaActivity : AppCompatActivity() {
    val rxPresenter=RxPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        rxPresenter.makeSubscribeToJustObservable(object:Observer<String>{
            override fun onComplete() {
                Log.v("RxJavaActivity","onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.v("RxJavaActivity","onSubscribe")
            }

            override fun onNext(t: String) {
                Log.v("RxJavaActivity","onNext")
            }

            override fun onError(e: Throwable) {
                Log.v("RxJavaActivity","onError")
            }

        })

    }
}
