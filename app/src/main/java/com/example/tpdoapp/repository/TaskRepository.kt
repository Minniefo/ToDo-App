package com.example.tpdoapp.repository

import com.example.tpdoapp.database.TaskDatabase
import com.example.tpdoapp.model.Task

class TaskRepository (private val db:TaskDatabase){
    suspend fun insertTask(task:Task)=db.getTaskDao().insertTask(task)
    suspend fun deleteTask(task:Task)=db.getTaskDao().deleteTask(task)
    suspend fun updateTask(task:Task)=db.getTaskDao().updateTask(task)

    fun getAllTasks()= db.getTaskDao().getAllTasks()
    fun searchTask(query:String?)=db.getTaskDao().searchTask(query)


}