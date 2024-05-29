package org.ibda.myguessgame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.ibda.myguessgame.databinding.FragmentNormalBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NormalFragment : Fragment(), TaskClickListener {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var vm : NormalViewModel
    private lateinit var binding : FragmentNormalBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentNormalBinding.inflate(inflater, container, false)
        val rootView = binding.root

        vm = ViewModelProvider(this).get(NormalViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        this.binding.normal = vm
        this.binding.lifecycleOwner = viewLifecycleOwner

        sharedViewModel.destination.observe(this.viewLifecycleOwner, Observer { destination ->
            vm.setDestination(destination)
        })

        this.vm.tasks.observe(this.viewLifecycleOwner, Observer { tasks ->
            recyclerView.adapter = TaskAdapter(tasks, vm.actionText(), this)
        })

        return rootView
    }
    override fun onTaskClick(taskId: Int) {
        val rootView = binding.root
        Log.d("NormalFragment", "Task ID clicked: $taskId")
        val action = NormalFragmentDirections
            .actionNormalFragmentToTaskDetailFragment(taskId)
        rootView.findNavController().navigate(action)
    }
}