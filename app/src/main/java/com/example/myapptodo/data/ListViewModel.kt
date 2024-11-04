package com.example.myapptodo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {

      val readAllData: LiveData<List<Task>>
      private val repo: ListRepo

      init {
          val listDao = TaskDatabase.getDatabase(application).listDao()
          repo = ListRepo(listDao)
          readAllData = repo.readAllData
      }

      fun addTask(task: Task){
          viewModelScope.launch(Dispatchers.IO) {
              repo.addTask(task)
          }
      }

}
