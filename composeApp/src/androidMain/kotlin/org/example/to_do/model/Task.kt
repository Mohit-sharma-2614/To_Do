package org.example.to_do.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val description: String
)

object task_db{
    var tasks = mutableListOf<Task>()
}