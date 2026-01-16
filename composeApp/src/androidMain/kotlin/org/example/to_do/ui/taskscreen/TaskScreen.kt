package org.example.to_do.ui.taskscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.to_do.model.Task
import org.example.to_do.ui.components.AddTaskDialog
import org.example.to_do.ui.components.TaskCard

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = org.koin.compose.koinInject()
) {
    val tasks by viewModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }
    
    Column(modifier = Modifier.padding(16.dp)) {
        
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchTasks(it)
            },
            label = { Text("Search by title") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(Modifier.height(8.dp))
        
        Button(onClick = { showDialog = true }) {
            Text("Add Task")
        }
        
        Spacer(Modifier.height(8.dp))
        
        tasks.forEach { task ->
            TaskCard(task)
        }
    }
    
    if (showDialog) {
        AddTaskDialog(
            onDismiss = { showDialog = false },
            onConfirm = {
                viewModel.createTask(it)
                showDialog = false
            }
        )
    }
}



