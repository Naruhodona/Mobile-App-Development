package org.ibda.myguessgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ResultViewModelFactory(private val resultMessage : String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(resultMessage) as T

        throw IllegalArgumentException("Unknown View Model")
    }

}