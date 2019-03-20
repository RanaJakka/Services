package com.example.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ScoreViewModel: ViewModel() {
    private var scoreTeamALiveData=MutableLiveData<Int>()
    private var scoreTeamBLiveData=MutableLiveData<Int>()
    var scoreTeamA:Int= 0

    set(value) {
        scoreTeamA=value
        scoreTeamALiveData.value=scoreTeamA
    }
    var scoreTeamB=0
    set(value){
        scoreTeamB=value
        scoreTeamBLiveData.value=scoreTeamB
    }

    fun getObservableScoreTeamA()=scoreTeamALiveData
    fun getObservableScoreTeamB()=scoreTeamBLiveData


}