package com.example.network.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Identifier

interface CardMetaData{
    val type:Int?
}

data class Employee (@SerializedName("id")
                     @Expose
                     var id:String,
                     @SerializedName("employee_name")
                     @Expose
                     var employeeName:String,
                     @SerializedName("employee_salary")
                     @Expose
                     var employeeSalary:String,
                     @SerializedName("employee_age")
                     @Expose
                     var employeeAge:Int,
                     @SerializedName("profile_image")
                     @Expose
                     var profileImage:String,
                     override var type: Int?=CARD_TYPE_ONE ): CardMetaData

const val CARD_TYPE_ONE:Int=1
const val CARD_TYPE_TWO:Int=2




