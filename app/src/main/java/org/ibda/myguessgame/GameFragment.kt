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




        return rootView
    }


}