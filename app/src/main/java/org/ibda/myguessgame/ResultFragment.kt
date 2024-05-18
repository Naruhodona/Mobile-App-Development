package org.ibda.myguessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private lateinit var vm: ResultViewModel
    private lateinit var vmFactory : ResultViewModelFactory

    private lateinit var wonLostTV: TextView
    private lateinit var startNewGameBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_result, container, false)

        wonLostTV = rootView.findViewById<TextView>(R.id.won_lost)
        startNewGameBtn = rootView.findViewById<Button>(R.id.start_game_btn)

        val resultMessage = ResultFragmentArgs.fromBundle(requireArguments()).result

        vmFactory = ResultViewModelFactory(resultMessage)
        vm = ViewModelProvider(this, vmFactory)
            .get(ResultViewModel::class.java)


//        wonLostTV.text = ResultFragmentArgs.fromBundle(requireArguments()).result
        wonLostTV.text = vm.result

        startNewGameBtn.setOnClickListener {
            rootView.findNavController()
                .navigate(R.id.action_resultFragment_to_gameFragment)
        }

        return rootView
    }
}