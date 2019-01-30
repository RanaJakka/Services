package com.example.rxj8934.recyclerview.service.recycler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rxj8934.recyclerview.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recycler_layout.*

class RecyclerActivity: AppCompatActivity() {
    private val list=ArrayList<Entry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_layout)
        recyclerInfo.layoutManager=LinearLayoutManager(this)
        val dataAdapte=DataAdapter()

        for (i in 0 until 15) {
            val r = (Math.random() * list.size).toInt()
            list.add(r,Entry("Item ${i}"))
        }
        dataAdapte.setData(list)
        recyclerInfo.adapter=dataAdapte
        recyclerInfo.addItemDecoration( DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Thread.sleep(2000)



    }

    inner class DataAdapter:MyAdapter<Entry,MyViewHolder>(){
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(this@RecyclerActivity).inflate(R.layout.recycler_item,viewGroup,false))
        }

        override fun onBindViewHolder(viewHomder: MyViewHolder, position: Int) {
            viewHomder.bindEntry(dataSource[position])
        }


    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView:TextView
        init {
            textView=itemView.findViewById(R.id.itemTextView)
            textView.setOnClickListener({

            })
        }
        fun bindEntry(entry:Entry){
            textView.text=entry.title
        }
        fun getTextView()=textView

    }
}