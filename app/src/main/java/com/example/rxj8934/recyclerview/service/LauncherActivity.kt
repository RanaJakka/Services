package com.example.rxj8934.recyclerview.service

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.example.rxj8934.R
import com.example.rxj8934.mvvm.base_feature.navigation.DeepLinkProcessor
import com.example.rxj8934.mvvm.base_feature.navigation.DefaultRouterHandler
import com.example.rxj8934.mvvm.router.MVVMRouter
import kotlinx.android.synthetic.main.launcher_activity.*

class LauncherActivity:AppCompatActivity() {
    private lateinit var conceptTitle:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        conceptTitle= resources.getStringArray(R.array.concepts)
        conceptsRecycler.layoutManager=GridLayoutManager(this,3)
        val click=object :AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getCentralNavigation().deepLinkProcessor.apply {
            this.elementAt(position).execute( this.elementAt(position).model.deepLinkUrl)
        }
                // https//rana.com/MVVMActivity
            }

        }
        conceptsRecycler.adapter=ConceptAdapter(getCentralNavigation(),click)

    }



    inner class ConceptAdapter(val conceptArray:DefaultRouterHandler,val onItemClickListener: AdapterView.OnItemClickListener): RecyclerView.Adapter<ConceptViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConceptViewHolder {
            return ConceptViewHolder(LayoutInflater.from(this@LauncherActivity).inflate(R.layout.concept_item,viewGroup,false),onItemClickListener)

        }

        override fun getItemCount(): Int {
            return conceptArray.deepLinkProcessor.size
        }

        override fun onBindViewHolder(viewHolder: ConceptViewHolder, p1: Int) {
            viewHolder.bind(conceptName = conceptArray.deepLinkProcessor.elementAt(p1).model.conceptName)

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


    data class LauncherModel(val conceptName:String,val deepLinkProcessor: DeepLinkProcessor)
    fun getCentralNavigation()= DefaultRouterHandler(getConceptList())


    fun getConceptList()= setOf<DeepLinkProcessor>(
            MVVMRouter(this@LauncherActivity.application),
            MVVMRouter(this@LauncherActivity.application),
            MVVMRouter(this@LauncherActivity.application)
    )
}