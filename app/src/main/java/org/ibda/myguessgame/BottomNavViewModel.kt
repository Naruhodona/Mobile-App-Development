package org.ibda.myguessgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavViewModel : ViewModel() {
    val destination = MutableLiveData<String>("")
    val back = MutableLiveData<Boolean>(false)

    fun backToHome(){
        this.back.value = true
    }
}