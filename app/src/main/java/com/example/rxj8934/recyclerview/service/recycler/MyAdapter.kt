package com.example.rxj8934.recyclerview.service.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.icu.lang.UCharacter.GraphemeClusterBreak.V

data class Entry(val title:String)


abstract class MyAdapter<S,VH:RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    protected val dataSource = ArrayList<S>()
    abstract override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VH
    abstract override fun onBindViewHolder(viewHomder: VH, position: Int)
    override fun getItemCount(): Int {
        return dataSource.size
    }
     fun addNewItem(position:Int,item:S){
         dataSource.add(position,item)
         notifyItemInserted(position)
     }
    fun removeItem(position:Int){
        dataSource.removeAt(position)
        notifyItemRemoved(position)
    }

    fun moveItem(currPosition:Int,toMovePosition:Int){
        movie(dataSource,currPosition,toMovePosition)
        notifyItemMoved(currPosition,toMovePosition)

    }
    private fun movie(list:ArrayList<S>,oldPosi:Int,newPosi:Int){
        val temp=list.removeAt(oldPosi)
        list.add(newPosi,temp)

    }

    fun setData(data: List<S>) {
        // Remove all deleted items.
        for (i in dataSource.size - 1 downTo 0) {
            if (getLocation(data, dataSource[i]) < 0) {
                removeItem(i)
            }
        }

        // Add and move items.
        for (i in data.indices) {
            val entity = data[i]
            val loc = getLocation(dataSource, entity)
            if (loc < 0) {
                addNewItem(i, entity)
            } else if (loc != i) {
                moveItem(i, loc)

            }
        }
    }

    private fun getLocation(data: List<S>, entity: S): Int {
        for (j in data.indices) {
            val newEntity = data[j]
            if (entity!!.equals(newEntity)) {
                return j
            }
        }

        return -1
    }

}