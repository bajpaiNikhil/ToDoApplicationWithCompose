package com.example.todoapplicationwithcompose.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


interface TodoRepoInterface {

    suspend fun insertTodo(toDo: ToDo)

    suspend fun deleteTodo(toDo: ToDo)

    suspend fun getTodoById(id: Int) : ToDo?

    fun getTodos() : Flow<List<ToDo>>

}