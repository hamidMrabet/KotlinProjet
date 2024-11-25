package com.example.myapptodo.data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptodo.R
import kotlin.collections.List as List

class TaskAdapter(private val clickListener: (Task) -> Unit): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var TasksList = emptyList<Task>()

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView =  itemView.findViewById(R.id.title_Viewtxt)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_ViewTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = TasksList[position]
        holder.titleTextView.text = currentTask.titleTask
        holder.descriptionTextView.text = currentTask.descriptionTask
        holder.itemView.setOnClickListener{clickListener(currentTask)}
    }

    override fun getItemCount()= TasksList.size

    fun setTasks(task: List<Task>){
        this.TasksList = task
        notifyDataSetChanged()
    }

}
