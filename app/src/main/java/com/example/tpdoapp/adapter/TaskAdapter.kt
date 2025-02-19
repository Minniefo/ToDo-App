package com.example.tpdoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tpdoapp.databinding.TaskLayoutBinding
import com.example.tpdoapp.fragments.HomeFragmentDirections
import com.example.tpdoapp.model.Task

class TaskAdapter :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    class TaskViewHolder(val itemBinding: TaskLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)
    
    private val differCallBack = object:DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
          return oldItem.id==newItem.id &&
                  oldItem.taskDesc == newItem.taskDesc &&
                  oldItem.taskTitle == newItem.taskTitle
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = differ.currentList[position]

        holder.itemBinding.noteTitle.text=currentTask.taskTitle
        holder.itemBinding.noteDesc.text=currentTask.taskDesc

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(currentTask)
            it.findNavController().navigate(direction)

        }
    }


}