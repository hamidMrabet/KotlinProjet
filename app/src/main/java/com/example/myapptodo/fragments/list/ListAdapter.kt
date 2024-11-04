package com.example.myapptodo.fragments.list
import android.view.LayoutInflater.
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptodo.R
import com.example.myapptodo.data.Task
import kotlin.collections.List as List

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }


    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.findViewById<String>(R.id.id_txt) = currentItem.id.toString()
        holder.itemView.findViewById<String>(R.id.title_txt) = currentItem.titleTask
        holder.itemView.findViewById<String>(R.id.discrip_txt) = currentItem.discriptionTask

    }

    fun setData(task: List<Task>){
       this.taskList = task
        notifyDataSetChanged()
    }

}
