package org.ibda.myguessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ibda.myguessgame.databinding.FragmentImportantBinding

class ImportantFragment : Fragment(), TaskClickListener {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var vm : ImportantViewModel
    private lateinit var binding : FragmentImportantBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentImportantBinding.inflate(inflater, container, false)
        val rootView = binding.root

        vm = ViewModelProvider(this).get(ImportantViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        this.binding.important = vm
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
        val action = ImportantFragmentDirections
            .actionImportantFragmentToTaskDetailFragment(taskId)
        rootView.findNavController().navigate(action)
    }
}