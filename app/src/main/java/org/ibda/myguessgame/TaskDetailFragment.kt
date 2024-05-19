package org.ibda.myguessgame

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import org.ibda.myguessgame.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDetailFragment()
    }

    private lateinit var vm: TaskDetailViewModel
    private lateinit var binding: FragmentTaskDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentTaskDetailBinding.inflate(inflater,container,false)
        var rootView = binding.root

        vm = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        return rootView
    }
}