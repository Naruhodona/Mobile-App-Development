package org.ibda.myguessgame

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.ibda.myguessgame.databinding.FragmentCreateTaskBinding
import org.ibda.myguessgame.databinding.FragmentHomeBinding

class CreateTaskFragment : Fragment() {
    private lateinit var vm : CreateTaskViewModel
    private lateinit var binding : FragmentCreateTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        val rootView = binding.root

        vm = ViewModelProvider(this).get(CreateTaskViewModel::class.java)

        this.binding.createtask = vm
        this.binding.lifecycleOwner = viewLifecycleOwner

        val categorySpinner: Spinner = binding.categorySpinner

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                vm.category.value = selectedCategory.lowercase()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        this.vm.move.observe(this.viewLifecycleOwner, Observer {newValue->
            if(newValue){
                val action = CreateTaskFragmentDirections.actionCreateTaskFragmentToHomeFragment()
                rootView.findNavController().navigate(action)
            }

        })


        return rootView
    }
}