package org.example.to_do.repository

import org.example.to_do.model.Task

interface TaskRepo {
    suspend fun getTasks(): List<Task>
    suspend fun searchTask(query: String): List<Task>
    suspend fun deleteTask(id: Int): Unit
    suspend fun updateTask(task: Task): Task
    suspend fun createTask(task: Task): Unit
}