package com.example.myapptodo.fragments.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptodo.R
import com.example.myapptodo.data.Task
import kotlinx.android.syntheytic.main.item_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {
    private var taskList = emptyList<Task>()
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val descriptionTextView: TextView
        val myLayout: LayoutInflater
        init {
              titleTextView = itemView.findViewById(R.id.title_Viewtxt)
              descriptionTextView = itemView.findViewById(R.id.description_ViewTxt)
              myLayout = itemView.findViewById(R.id.item_Layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.titleTextView.text = currentItem.titleTask
        holder.descriptionTextView.text = currentItem.descriptionTask
        holder.itemView.item_Layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = taskList.size

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}
