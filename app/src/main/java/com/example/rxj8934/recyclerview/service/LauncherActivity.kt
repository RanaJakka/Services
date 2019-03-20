package com.example.rxj8934.recyclerview.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.example.rxj8934.R
import com.example.rxj8934.recyclerview.service.fragments.MyFragmentActivity
import com.example.rxj8934.recyclerview.service.handlers.example1.HandlerActivity
import com.example.rxj8934.recyclerview.service.services.BoundService
import com.example.rxj8934.recyclerview.service.services.IntentSeriviceActivity
import kotlinx.android.synthetic.main.launcher_activity.*

class LauncherActivity:AppCompatActivity() {
    private lateinit var conceptTitle:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        conceptTitle= resources.getStringArray(R.array.concepts)
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
                    "Fragments"->{
                        startActivity(Intent(this@LauncherActivity,MyFragmentActivity::class.java))
                    }
                    "BluePrintOfHandlers"->{

                        /*
                        // for deeplink actvity
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://rana.com/jakka")
                        startActivity(intent)
                        */

                        // retrofit
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://rana.com/network")
                        startActivity(intent)

                        //startActivity(Intent(this@LauncherActivity,HandlerActivity::class.java))
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