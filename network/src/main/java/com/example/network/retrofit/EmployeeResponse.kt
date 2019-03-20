package com.example.network.retrofit

import android.util.Log

class EmployeeResponse(callBack:RespinseCallBack<List<Employee>>): ProjectResponse<List<Employee>>(callBack) {
    val TAG="EmployeeResponse"

    fun makeCall(){
        ( makeNetWorkCall.makeAsynchroniousCalla()).apply {
            this.create(RetrofitRequest::class.java).apply {
                this@EmployeeResponse.apply {
                }
                this.getEmployeeRepo("employees").enqueue(this@EmployeeResponse)
            }
        }
    }
}