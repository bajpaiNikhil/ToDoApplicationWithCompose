package com.example.todoapplicationwithcompose.ui.theme.toto_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplicationwithcompose.TodoRepo
import com.example.todoapplicationwithcompose.data.ToDo
import com.example.todoapplicationwithcompose.data.TodoRepoInterface
import com.example.todoapplicationwithcompose.utils.Routes
import com.example.todoapplicationwithcompose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.annotation.meta.When
import javax.inject.Inject


@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository : TodoRepoInterface
): ViewModel() {

    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: ToDo?= null


    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnTodoClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }

            is TodoListEvent.OnDeleteTodoClick ->{
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "ToDo deleted",
                        action = "Undo"
                    ))
                }
            }

            is TodoListEvent.OnAddTodoClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TodoListEvent.OnDoneChange ->{
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
            is TodoListEvent.OnUndoDeleteClicked ->{
                deletedTodo?.let {todo->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }

        }
    }


    private fun sendUiEvent(event : UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}