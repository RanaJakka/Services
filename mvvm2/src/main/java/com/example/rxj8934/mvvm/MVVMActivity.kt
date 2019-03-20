package com.example.rxj8934.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import kotlinx.android.synthetic.main.activity_mvvm.*

class MVVMActivity : AppCompatActivity() {
    lateinit var scoreModel:ScoreViewModel
    private val TAG:String=MVVMActivity::class.java.canonicalName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG,"onCreate")
        setContentView(R.layout.activity_mvvm)
         scoreModel= ViewModelProviders.of(this).get(ScoreViewModel::class.java)
        scoreModel.getObservableScoreTeamA().observe(this, Observer {
            teamAScore.text="${it}"

        })
        scoreModel.getObservableScoreTeamB().observe(this, Observer {
            teamBScore.text="${it}"
        })

        teamA_threePaoints.setOnClickListener({
            scoreModel.postValueTeamA(3)
        })
        teamA_twoPaoints.setOnClickListener({
            scoreModel.postValueTeamA(2)
        })
        teamA_freePaoints.setOnClickListener({
            scoreModel.postValueTeamA(0)
        })

        teamB_threePaoints.setOnClickListener({
            scoreModel.postValueTeamB(3)
        })
        teamB_twoPaoints.setOnClickListener({
            scoreModel.postValueTeamB(2)
        })
        teamB_freePaoints.setOnClickListener({
            scoreModel.postValueTeamB(0)
            //scoreModel.runIterator()
        })
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG,"onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG,"onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(TAG,"onRestart")
    }

    // TODO(NOTE): if you implement this one means, You are overriding configuration changes. So you are avoiding activity recreating..
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
            Log.v(TAG,"LANDSCAPE")
        else
            Log.v(TAG,"PORTRAIT")
    }






}
