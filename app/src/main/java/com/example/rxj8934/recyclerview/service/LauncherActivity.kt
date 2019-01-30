package com.example.rxj8934.recyclerview.service

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import com.example.rxj8934.recyclerview.R
import com.example.rxj8934.recyclerview.service.recycler.RecyclerActivity
import com.example.rxj8934.recyclerview.service.services.BoundService
import com.example.rxj8934.recyclerview.service.services.IntentSeriviceActivity
import kotlinx.android.synthetic.main.launcher_activity.*
import java.util.*

class LauncherActivity:AppCompatActivity() {
    private val conceptTitle= arrayOf<String>("Bound Service","IntentService")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        conceptsRecycler.layoutManager=LinearLayoutManager(this)
        val click=object :AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(conceptTitle[position]){
                    "Bound Service"->{
                        startActivity(Intent(this@LauncherActivity,BoundService::class.java))


                    }
                    "IntentService"->{
                        startActivity(Intent(this@LauncherActivity,IntentSeriviceActivity::class.java))
                    }

                }
            }

        }
        conceptsRecycler.adapter=ConceptAdapter(conceptTitle,click)

    }



    inner class ConceptAdapter(val conceptArray:Array<String>,val onItemClickListener: AdapterView.OnItemClickListener): RecyclerView.Adapter<ConceptViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConceptViewHolder {
            return ConceptViewHolder(LayoutInflater.from(this@LauncherActivity).inflate(R.layout.concept_item,viewGroup,false),onItemClickListener)

        }

        override fun getItemCount(): Int {
            return conceptArray.size
        }

        override fun onBindViewHolder(viewHolder: ConceptViewHolder, p1: Int) {
            viewHolder.bind(conceptName = conceptArray[p1])

        }


    }
    inner class ConceptViewHolder(itemView: View,val onItemClickListener: AdapterView.OnItemClickListener) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        override fun onClick(v: View) {
        onItemClickListener.onItemClick(null,v,adapterPosition,v.id.toLong())
        }

        val conceptText: TextView
        init {
            conceptText=itemView.findViewById(R.id.conceptName)
            conceptText.setOnClickListener(this)
        }
        fun bind(conceptName:String){
            conceptText.text=conceptName
        }


    }
}