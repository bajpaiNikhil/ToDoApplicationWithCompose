package com.example.todoapplicationwithcompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDo: ToDo)

    @Delete
    suspend fun deleteTodo(toDo: ToDo)


    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int) : ToDo?

    @Query("SELECT * FROM todo")
    fun getTodos() : Flow<List<ToDo>>


}