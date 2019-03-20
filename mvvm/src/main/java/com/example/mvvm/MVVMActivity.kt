package com.example.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rxj8934.R
import kotlinx.android.synthetic.main.activity_main.*

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var scoreModel:ScoreViewModel
        setContentView(R.layout.activity_main)
         scoreModel=ViewModelProviders.of(this).get(ScoreViewModel::class.java)
        scoreModel.getObservableScoreTeamA().observe(this, Observer {
            teamAScore.text="${it}"

        })
        scoreModel.getObservableScoreTeamB().observe(this, Observer {
            teamBScore.text="${it}"
        })
    }

}
