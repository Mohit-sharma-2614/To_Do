package org.example.to_do.di

import org.example.to_do.repository.TaskRepo
import org.example.to_do.repository.repo_impl.TaskRepoImpl
import org.example.to_do.ui.taskscreen.TaskViewModel
import org.koin.dsl.module

val commonModule = module {
    
    single<TaskRepo> {
        TaskRepoImpl()
    }
    
    factory {
        TaskViewModel(get())
    }
}