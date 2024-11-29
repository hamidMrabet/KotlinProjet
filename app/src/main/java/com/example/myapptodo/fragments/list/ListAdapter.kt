package com.example.myapptodo.fragments.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptodo.R
import com.example.myapptodo.data.Task
import com.example.myapptodo.fragments.list.ListAdapter.OnItemClickListener


class ListAdapter : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(task: Task)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    private var taskList = emptyList<Task>()

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val descriptionTextView: TextView

        init {
              titleTextView = itemView.findViewById(R.id.title_View)
              descriptionTextView = itemView.findViewById(R.id.description_View)
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
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount() = taskList.size

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}
