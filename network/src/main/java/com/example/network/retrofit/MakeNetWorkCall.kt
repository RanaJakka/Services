package com.example.network.retrofit

import android.widget.TableRow
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

interface RespinseCallBack<T:Any>{
    fun notifyExceptions(errorCode:Int,t:Throwable)
    fun notifyFailure(errorCode:Int,message:String)
    fun notifyWithData(t:T)
}

class MakeNetWorkCall {
    private val baseURL:String="http://dummy.restapiexample.com/"

    fun setUpRetrofit():Retrofit{

        val logging = HttpLoggingInterceptor()
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
// add your other interceptors …

// add logging as last interceptor

        val httpClient= OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
        httpClient.interceptors().add(logging)
        return Retrofit.Builder().apply {
            baseUrl(baseURL)
            addConverterFactory(GsonConverterFactory.create())
            client(httpClient.build())
        }.build()

    }


     fun  makeAsynchroniousCalla()= setUpRetrofit()

}