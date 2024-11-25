package com.example.myapptodo.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapptodo.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapptodo.data.TaskAdapter
import com.example.myapptodo.data.TaskViewModel


class ListFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = TaskAdapter()
        val recyclerView = view.recyclerview
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer { task ->
            adapter.setTasks(task)
        })

        view.findViewById<Button>(R.id.Fab_Button).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

}


