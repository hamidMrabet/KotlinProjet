package com.example.myapptodo.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapptodo.R
import com.example.myapptodo.data.Task
import com.example.myapptodo.data.TaskViewModel
import androidx.compose.material3.AlertDialog
import com.example.myapptodo.data.TaskAdapter


class UpdateFragment(private val currentTask: Task): Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.findViewById<EditText>(R.id.update_Title).setText(currentTask.titleTask)
        view.findViewById<EditText>(R.id.update_Description).setText(currentTask.descriptionTask)

        view.findViewById<View>(R.id.update_btn).setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val titleTask = view?.findViewById<EditText>(R.id.update_Title)?.text.toString()
        val descriptionTask = view?.findViewById<EditText>(R.id.update_Description).toString()

        if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty()) {
            val updatedTask = Task(currentTask.id, titleTask, descriptionTask)
            taskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Tarefa Atualizada!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Todos os Campos São Obrigatorios!.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(titleTask: String, descriptionTask: String): Boolean {
        return !(titleTask.isEmpty() && descriptionTask.isEmpty())
    }

    override fun getView(po): View? {
        var myView = layoutInflater.inflate(R.layout.item_layout, null)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val myTask = TaskAdapter[position]

        myView.delete_Btn.setOnClickListener {

        }

    }


@Composable
private fun deleteTask() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("Sim") {_,_ ->
        taskViewModel.deleteTask(currentTask)
        Toast.makeText(
            requireContext(),
            "Tarefa Excluida: ${currentTask.titleTask}", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
    builder.setNegativeButton("Não") {_,_ -> }
    builder.setTitle("Excluir ${currentTask.titleTask}?")
    builder.setMessage("Are you sure you want to delete ${currentTask.titleTask}?")
    builder.create().show()
}
}