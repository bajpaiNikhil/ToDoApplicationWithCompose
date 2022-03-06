package com.example.todoapplicationwithcompose.ui.theme.toto_list

import com.example.todoapplicationwithcompose.data.ToDo

sealed class TodoListEvent{

    data class OnDeleteTodoClick(val todo: ToDo) : TodoListEvent()
    data class OnDoneChange(val todo: ToDo , val isDone : Boolean) : TodoListEvent()
    object OnUndoDeleteClicked : TodoListEvent()
    data class OnTodoClick(val todo:ToDo) : TodoListEvent()
    object OnAddTodoClick : TodoListEvent()


}
