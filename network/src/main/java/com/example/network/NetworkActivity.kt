package com.example.network

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import com.example.network.retrofit.Employee
import com.example.network.retrofit.EmployeeResponse
import com.example.network.retrofit.NetworkViewModel
import com.example.network.retrofit.RespinseCallBack
import kotlinx.android.synthetic.main.activity_network.*

class NetworkActivity : AppCompatActivity() {
    private lateinit var networkViewModel:NetworkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        Log.v("-->","onCreate")
        networkViewModel=ViewModelProviders.of(this).get(NetworkViewModel::class.java)

        employeeList.layoutManager=LinearLayoutManager(this)
        employeeList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        EmployeeResponse(object:RespinseCallBack<List<Employee>>{
            override fun notifyExceptions(errorCode: Int, t: Throwable) {
                Log.v("Success Response","${t}")
            }

            override fun notifyFailure(errorCode: Int, message: String) {
                Log.v("Success Response","${message}")
            }

            override fun notifyWithData(t: List<Employee>) {
                Log.v("Success Response","${t.size}")
                val adapter=NetworkAdapter(ArrayList(t))
                employeeList.adapter= adapter
                ItemTouchHelper(SwipeToDeleteCallback(adapter = adapter)).attachToRecyclerView(employeeList)
            }
        }).makeCall()
    }

    override fun onStart() {
        super.onStart()

        Log.v("-->","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("-->","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("-->","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("-->","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("-->","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("-->","onDestroy")
    }

    inner class SwipeToDeleteCallback(val adapter:NetworkAdapter): ItemTouchHelper.SimpleCallback(0,(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) ){
        private val icon:Drawable
        private val backgroundColor:ColorDrawable
        init {
            icon=ContextCompat.getDrawable(this@NetworkActivity,R.drawable.abc_ic_arrow_drop_right_black_24dp)!!
            backgroundColor= ColorDrawable(Color.RED)
        }

        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position=viewHolder.adapterPosition
            adapter.deleteItemFromList(position)
        }

        override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            val itemView=viewHolder.itemView
            val backgroundCornerOffset=20

            if(dX>0){ // swipe right
                backgroundColor.setBounds(itemView.left,itemView.top,itemView.left+(dX+backgroundCornerOffset).toInt(),itemView.bottom)
            } else if(dX<0){
                backgroundColor.setBounds(itemView.right+(dX-backgroundCornerOffset).toInt(),itemView.top,itemView.right,itemView.bottom)
            } else{
                backgroundColor.setBounds(0,0,0,0)

            }
            backgroundColor.draw(c)
        }
    }
}
