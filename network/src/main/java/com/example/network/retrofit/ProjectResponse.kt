package com.example.network.retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ProjectResponse<T:Any>(val responseCallBack:RespinseCallBack<T> ):Callback<T> {
      lateinit var dataClass:T
    val makeNetWorkCall=MakeNetWorkCall()

    override fun onFailure(call: Call<T>, t: Throwable) {
        responseCallBack.notifyExceptions(4000,t)
        Log.v("ON RESPONSE FAILURE","${t.printStackTrace()}")
    }
    override fun onResponse(call: Call<T>, response: Response<T>) {

        Log.v("ON RESPONSE SUCCESS","${response.body()}")
        if(response.isSuccessful){
            response.body()?.let {
                dataClass=it
                responseCallBack.notifyWithData(dataClass)
            }
        }else
        {
            response?.apply {
                responseCallBack.notifyFailure(this.code(),this.message())
            }
            Log.v(" SUCCESS FAILURE","${response.body()}")
            // failure case here
        }
    }

}