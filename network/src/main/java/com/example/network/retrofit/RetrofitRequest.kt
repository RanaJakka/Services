package com.example.network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseRequest{

}



interface RetrofitRequest {

    //this is the my first Retrofit call here..
    @GET("/api/v1/{user}")
    fun getEmployeeRepo(@Path("user") user:String):Call<List<Employee>>


}