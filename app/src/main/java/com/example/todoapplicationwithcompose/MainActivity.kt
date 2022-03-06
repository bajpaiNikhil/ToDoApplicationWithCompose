package com.example.todoapplicationwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapplicationwithcompose.ui.theme.ToDoApplicationWithComposeTheme
import com.example.todoapplicationwithcompose.ui.theme.add_edit_todo.AddEditTodoScreen
import com.example.todoapplicationwithcompose.ui.theme.toto_list.TodoListScreen
import com.example.todoapplicationwithcompose.utils.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoApplicationWithComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Routes.TODO_LIST ){
                    composable(Routes.TODO_LIST){
                        TodoListScreen(onNavigate = {
                            navController.navigate(it.route)
                        }
                        )
                    }
                    composable(route = Routes.ADD_EDIT_TODO +"?todoId={todoId}" ,
                    arguments = listOf(
                        navArgument(name = "todoId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )){
                        AddEditTodoScreen(onPopBackStack = { navController.popBackStack() })
                    }
                }

            }
        }
    }
}
