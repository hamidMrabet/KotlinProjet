package com.example.myapptodo.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapptodo.R
import com.example.myapptodo.data.Task
import com.example.myapptodo.data.TaskViewModel


class UpdateFragment: Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        view.findViewById<EditText>(R.id.update_Title).setText(args.currentTask.titleTask)
        view.findViewById<EditText>(R.id.update_Description).setText(args.currentTask.descriptionTask)
        val updateButton = view.findViewById<Button>(R.id.update_btn)

        updateButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return view
    }
        private fun updateItem() {
                val titleTask = view?.findViewById<EditText>(R.id.update_Title)?.text.toString()
                val descriptionTask = view?.findViewById<EditText>(R.id.update_Description).toString()

            if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty()) {
                val updatedTask = Task(args.currentTask.id, titleTask, descriptionTask)
                taskViewModel.updateTask(updatedTask)
                Toast.makeText(requireContext(), "Tarefa Atualizada!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Todos os Campos São Obrigatorios!.", Toast.LENGTH_SHORT).show()
            }

        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.delete_menu, menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.menu_delete) {
                deleteTask()
            }
            return super.onOptionsItemSelected(item)
        }

        private fun deleteTask() {
            val builder = AlertDialog.Builder(requireContext())

                builder.setPositiveButton("Sim") { _, _ ->
                    taskViewModel.deleteTask(args.currentTask)
                    Toast.makeText(requireContext(), "Tarefa Excluida: ${args.currentTask.titleTask}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
                builder.setNegativeButton("Não") { _, _ -> }
                builder.setTitle("Delete ${args.currentTask.titleTask}?")
                builder.setMessage("Confirmar o Cancelamento ${args.currentTask.titleTask}?")
                builder.create().show()

    }
}
