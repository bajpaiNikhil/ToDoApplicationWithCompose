package com.example.todoapplicationwithcompose

import com.example.todoapplicationwithcompose.data.ToDo
import com.example.todoapplicationwithcompose.data.TodoDao
import com.example.todoapplicationwithcompose.data.TodoRepoInterface
import kotlinx.coroutines.flow.Flow

class TodoRepo(
    private val dao : TodoDao
) : TodoRepoInterface {

    override suspend fun insertTodo(toDo: ToDo) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteTodo(toDo: ToDo) {
        dao.deleteTodo(toDo)
    }

    override suspend fun getTodoById(id: Int): ToDo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<ToDo>> {
        return dao.getTodos()
    }

}