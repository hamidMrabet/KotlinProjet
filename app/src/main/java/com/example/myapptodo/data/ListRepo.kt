package com.example.myapptodo.data

import androidx.lifecycle.LiveData

class ListRepo(private val taskDao: ListDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
}