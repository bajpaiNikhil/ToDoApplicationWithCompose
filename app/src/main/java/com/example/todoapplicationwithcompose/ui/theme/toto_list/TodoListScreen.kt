package com.example.todoapplicationwithcompose.ui.theme.toto_list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapplicationwithcompose.utils.UiEvent
import kotlinx.coroutines.flow.collect
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit , 
    viewModel: TodoListViewModel = hiltViewModel()
){
    val todos  = viewModel.todos.collectAsState(initial = emptyList())
    var scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){

        viewModel.uiEvent.collect { event->
            when(event){
                is UiEvent.ShowSnackBar ->{
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message ,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(TodoListEvent.OnUndoDeleteClicked)
                    }
                }
                is UiEvent.Navigate ->onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState ,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TodoListEvent.OnAddTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Todo Item To add"
                )
            }}
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(todos.value){ todo ->
                TodoItem(todo = todo,
                    onEvent = viewModel::onEvent ,
                    modifier = Modifier.fillMaxWidth().clickable{
                        viewModel.onEvent(TodoListEvent.OnTodoClick(todo))
                    }
                        .padding(16.dp)
                )
            }
        }
    }
}