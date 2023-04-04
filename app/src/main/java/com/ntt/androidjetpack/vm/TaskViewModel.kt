package com.ntt.androidjetpack.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ntt.androidjetpack.db.TaskDatabase

class TaskViewModel(private val app: Application) : AndroidViewModel(app) {

    private val taskDao = TaskDatabase.getInstance(app, viewModelScope).getTaskDao()

    val tasks = taskDao.getTasksLiveData()

    val taskPaging = Pager(PagingConfig(pageSize = 20)) {
        taskDao.getTaskByPaging()
    }.flow.cachedIn(viewModelScope)
}