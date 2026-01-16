package org.example.to_do.repository.repo_impl

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.example.to_do.model.Task
import org.example.to_do.model.task_db
import org.example.to_do.repository.TaskRepo
import dev.gitlive.firebase.firestore.firestore
import kotlinx.serialization.Serializable

class TaskRepoImpl : TaskRepo {
    
    // Access the native Firestore instance via GitLive wrapper
    private val firestore = Firebase.firestore
    private val tasksCollection = firestore.collection("tasks")
    
    override suspend fun getTasks(): List<Task> {
        return try {
            // .get() fetches the QuerySnapshot from Firestore
            val snapshot = tasksCollection.get()
            // .data<Task>() uses kotlinx.serialization to map the document
            snapshot.documents.map { it.data() }
        } catch (e: Exception) {
            // Handle network or permission errors
            emptyList()
        }
    }
    
    override suspend fun searchTask(query: String): List<Task> {
        // Firestore doesn't support 'case-insensitive contains' natively for strings
        // So we fetch and filter locally, or use a specific Firestore query for exact matches
        val allTasks = getTasks()
        if (query.isBlank()) return allTasks
        
        return allTasks.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }
    
    override suspend fun deleteTask(id: Int) {
        // We use the Task ID as the document path/name
        val document = tasksCollection.document(id.toString())
        
        if (!document.get().exists) {
            throw IllegalArgumentException("Task with id $id not found")
        }
        
        document.delete()
    }
    
    override suspend fun updateTask(task: Task): Task {
        val document = tasksCollection.document(task.id.toString())
        
        if (!document.get().exists) {
            throw NoSuchElementException("Task with id ${task.id} not found")
        }
        
        // .set(task) overwrites the existing document with the updated object
        document.set(task)
        return task
    }
    
    override suspend fun createTask(task: Task) {
        val document = tasksCollection.document(task.id.toString())
        
        // Safety check to prevent overwriting
        if (document.get().exists) {
            throw IllegalArgumentException("Task with id ${task.id} already exists")
        }
        
        document.set(task)
    }
}