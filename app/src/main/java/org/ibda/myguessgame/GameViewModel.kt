package org.ibda.myguessgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val words = listOf<String>("Stefanus", "Cipto", "Kurniawan")

    val secretWord = words.random().uppercase()
//    var secretWordDisplay = ""
    var secretWordDisplay = MutableLiveData<String>("")
    var correctGuess = ""
//    var incorrectGuess = ""
    var incorrectGuess = MutableLiveData<String>("")
//    var livesLeft = 5
    var livesLeft = MutableLiveData<Int>(5)

    var guessLetter = MutableLiveData<String>("")
    var gameOver = MutableLiveData<Boolean>(false)

    init {
//        secretWordDisplay = this.getDisplayedSecretWord()
        this.secretWordDisplay.value = this.getDisplayedSecretWord()
    }

//    fun updateScreen(wordTV : TextView, livesTV : TextView, incorrectGuessTV: TextView) {
//        wordTV.text = this.secretWordDisplay
//        livesTV.text = "Quota Permainan Sisa ${livesLeft} kali."
//        incorrectGuessTV.text = "Incorrect Guess : ${incorrectGuess}"
//    }

    fun getDisplayedSecretWord() : String {
        var display = ""

        secretWord.forEach {
            when(correctGuess.contains(it)) {
                true -> display += it
                false -> display += "_"
            }
        }

        return display
    }

    fun makeGuess(guess: String) {
        if(guess.length == 1)
        {
            if(secretWord.contains(guess)) {
                correctGuess += guess
                secretWordDisplay.value = getDisplayedSecretWord()
            } else {
                incorrectGuess.value += "$guess "
//                livesLeft--
                this.livesLeft.value = livesLeft.value?.minus(1)
            }
        }
    }

    fun isWon() : Boolean {
        return secretWord.equals(secretWordDisplay.value, true)
    }

    fun isLost() : Boolean {
        if(livesLeft.value ?: 0 <= 0)
            return true
        else
            return false
    }

    fun getWonLossMessage() : String {
        var message = ""

        if(isWon())
            message = "You Won!"
        else if(isLost())
            message = "You Lost!"

        message += " The Word is $secretWord"

        return message
    }

    fun checkGuess() {
        this.makeGuess(this.guessLetter.value.toString().uppercase())
        this.guessLetter.value = ""

        if(this.isWon() || this.isLost())
        {
            this.gameOver.value = true
//            val action = GameFragmentDirections
//                .actionGameFragmentToResultFragment(this.getWonLossMessage())
//            rootView.findNavController().navigate(action)
        }
    }

}