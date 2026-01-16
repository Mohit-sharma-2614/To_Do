package org.example.to_do.ui.taskscreen

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.to_do.model.Task
import org.example.to_do.repository.TaskRepo

class TaskViewModel(
    private val repo: TaskRepo
) {
    
    private val viewModelScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )
    
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repo.getTasks()
        }
    }
    
    fun searchTasks(query: String) {
        viewModelScope.launch {
            _tasks.value = repo.searchTask(query)
        }
    }
    
    fun createTask(task: Task) {
        viewModelScope.launch {
            try {
                repo.createTask(task)
                loadTasks()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    
    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                repo.updateTask(task)
                loadTasks()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    
    fun deleteTask(id: Int) {
        viewModelScope.launch {
            try {
                repo.deleteTask(id)
                loadTasks()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    
    fun clear() {
        viewModelScope.cancel()
    }
}
