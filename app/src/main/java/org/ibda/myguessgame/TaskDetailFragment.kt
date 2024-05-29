package org.ibda.myguessgame

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import org.ibda.myguessgame.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {
    private lateinit var vm: TaskDetailViewModel
    private lateinit var binding: FragmentTaskDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentTaskDetailBinding.inflate(inflater,container,false)
        val rootView = binding.root

        vm = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        val taskId = TaskDetailFragmentArgs.fromBundle(requireArguments()).taskId
        vm.id.value = taskId
        vm.getTask(taskId)

        this.binding.taskdetail = vm
        this.binding.lifecycleOwner = viewLifecycleOwner

        this.vm.normal.observe(this.viewLifecycleOwner, Observer {newValue->
            if(newValue) {
                val action = TaskDetailFragmentDirections
                    .actionTaskDetailFragmentToNormalFragment()
                this.vm.normal.value = false
                rootView.findNavController().navigate(action)
            }
        })

        this.vm.important.observe(this.viewLifecycleOwner, Observer {newValue->
            if(newValue) {
                val action = TaskDetailFragmentDirections
                    .actionTaskDetailFragmentToImportantFragment()
                this.vm.important.value = false
                rootView.findNavController().navigate(action)
            }
        })

        this.vm.urgent.observe(this.viewLifecycleOwner, Observer {newValue->
            if(newValue) {
                val action = TaskDetailFragmentDirections
                    .actionTaskDetailFragmentToUrgentFragment()
                this.vm.urgent.value = false
                rootView.findNavController().navigate(action)
            }
        })


        return rootView
    }
}