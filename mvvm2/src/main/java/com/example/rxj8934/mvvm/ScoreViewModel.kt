package com.example.rxj8934.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ScoreViewModel: ViewModel() {
    companion object {
        val TAG=ScoreViewModel::class.java.simpleName
    }

    private var scoreTeamALiveData=MutableLiveData<Int>()
    private var scoreTeamBLiveData=MutableLiveData<Int>()

    var scoreTeamA:Int=0

    var scoreTeamB:Int=0

    init {
        scoreTeamALiveData.postValue(scoreTeamA)
        scoreTeamBLiveData.postValue(scoreTeamB)
    }
    fun postValueTeamA(value:Int){
        scoreTeamA=scoreTeamA+value
        scoreTeamALiveData.value=(scoreTeamA)
    }
    fun postValueTeamB(value:Int){
        scoreTeamB=scoreTeamB+value
        scoreTeamBLiveData.value=(scoreTeamB)
    }
    fun getObservableScoreTeamA():LiveData<Int>{return scoreTeamALiveData}
    fun getObservableScoreTeamB():LiveData<Int>{return scoreTeamBLiveData}

    fun runIterator(){
        val arrayList=ArrayList<Int>()
        arrayList.add(1)
        arrayList.add(11)
        arrayList.add(12)
        arrayList.add(13)
        val iterator=arrayList.iterator()
        while (true){
            System.out.println("${iterator.next()}")
        }
    }


}