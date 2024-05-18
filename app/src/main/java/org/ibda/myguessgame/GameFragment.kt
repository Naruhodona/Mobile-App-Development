package org.ibda.myguessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.ibda.myguessgame.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var vm : GameViewModel
    private lateinit var binding : FragmentGameBinding

//    private lateinit var wordTV : TextView
//    private lateinit var livesTV : TextView
//    private lateinit var incorrectGuessTV: TextView
//    private lateinit var guessET : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val rootView =  inflater.inflate(R.layout.fragment_game, container, false)
        this.binding = FragmentGameBinding.inflate(inflater, container, false)
        val rootView = binding.root

        vm = ViewModelProvider(this).get(GameViewModel::class.java)

        this.binding.game = vm
        this.binding.lifecycleOwner = viewLifecycleOwner

        vm.gameOver.observe(this.viewLifecycleOwner, Observer {
            newValue ->
            if(newValue)
            {
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(this.vm.getWonLossMessage())
                rootView.findNavController().navigate(action)
            }
        })




//        val guessBtn = rootView.findViewById<Button>(R.id.guess_btn)
//        wordTV = rootView.findViewById<TextView>(R.id.word)
//        livesTV = rootView.findViewById<TextView>(R.id.lives)
//        incorrectGuessTV = rootView.findViewById<TextView>(R.id.incorrect_guesses)
//        guessET = rootView.findViewById<EditText>(R.id.guess)


//        this.secretWordDisplay = this.getDisplayedSecretWord()
//        this.updateScreen()

//        binding.guessBtn.setOnClickListener {
//            this.vm.makeGuess(binding.guess.text.toString().uppercase())
//            this.binding.guess.text = null
////            updateScreen()
//
//            if(this.vm.isWon() || this.vm.isLost())
//            {
//                val action = GameFragmentDirections
//                    .actionGameFragmentToResultFragment(this.vm.getWonLossMessage())
//                rootView.findNavController().navigate(action)
//            }
//        }

//        vm.livesLeft.observe(this.viewLifecycleOwner, Observer { newValue ->
//            binding.lives.text = "Quota Permainan Sisa $newValue kali."
//        })
//
//        vm.secretWordDisplay.observe(viewLifecycleOwner, Observer { newValue ->
//            binding.word.text = newValue
//        })




//        guessBtn.setOnClickListener {
//            this.vm.makeGuess(this.guessET.text.toString().uppercase())
//            this.guessET.text = null
//            updateScreen()
//
//            if(this.vm.isWon() || this.vm.isLost())
//            {
//                val action = GameFragmentDirections
//                    .actionGameFragmentToResultFragment(this.vm.getWonLossMessage())
//                rootView.findNavController().navigate(action)
//            }
//        }

//        vm.livesLeft.observe(this.viewLifecycleOwner, Observer { newValue ->
//            livesTV.text = "Quota Permainan Sisa $newValue kali."
//        })
//
//        vm.secretWordDisplay.observe(viewLifecycleOwner, Observer { newValue ->
//            wordTV.text = newValue
//        })



        return rootView
    }

//    fun updateScreen() {
////        this.wordTV.text = this.vm.secretWordDisplay
////        this.livesTV.text = "Quota Permainan Sisa ${vm.livesLeft} kali."
////        this.incorrectGuessTV.text = "Incorrect Guess : ${vm.incorrectGuess}"
//    }


}