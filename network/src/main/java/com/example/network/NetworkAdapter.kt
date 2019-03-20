package com.example.network

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.network.retrofit.CARD_TYPE_ONE
import com.example.network.retrofit.CARD_TYPE_TWO
import com.example.network.retrofit.CardMetaData
import com.example.network.retrofit.Employee

class NetworkAdapter(var list:ArrayList<CardMetaData>):RecyclerView.Adapter<NetworkAdapter.BaseViewHolder<Employee>>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<Employee> {
       return  when(viewType){
            CARD_TYPE_ONE-> ViewHolderOne(LayoutInflater.from(viewGroup.context).inflate(R.layout.employee_item,viewGroup,false))
           CARD_TYPE_TWO->ViewHolderTwo(LayoutInflater.from(viewGroup.context).inflate(R.layout.employee_item2,viewGroup,false))
           else -> ViewHolderTwo(LayoutInflater.from(viewGroup.context).inflate(R.layout.employee_item2,viewGroup,false))
        }


    }
    override fun onBindViewHolder(p0: BaseViewHolder<Employee>, p1: Int) {
        p0.bindDataToView(list[p1]as Employee)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
       return  if((list[position] as Employee).employeeAge<25) CARD_TYPE_ONE else CARD_TYPE_TWO

    }


    abstract inner class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){
        abstract fun bindDataToView(t:T)
    }
    inner class ViewHolderOne(itemView: View) : BaseViewHolder<Employee>(itemView) {
        val employeeName:TextView
        init {
            employeeName=itemView.findViewById(R.id.emplyeeName)

        }
        override fun bindDataToView(t: Employee) {
            employeeName.text=t.employeeName
        }

    }

    inner class ViewHolderTwo(itemView: View) : BaseViewHolder<Employee>(itemView){
        val employeeName:TextView
        init {
            employeeName=itemView.findViewById(R.id.emplyeeName)

        }

        override fun bindDataToView(t: Employee) {
            employeeName.text=t.employeeName
        }




    }

    fun deleteItemFromList(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }



}