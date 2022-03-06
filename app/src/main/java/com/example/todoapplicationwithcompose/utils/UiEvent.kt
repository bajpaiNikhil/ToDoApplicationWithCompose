package com.example.todoapplicationwithcompose.utils

import android.os.Message

sealed class UiEvent{

    object PopBackStack : UiEvent()
    data class  Navigate(val route : String) : UiEvent()
    data class ShowSnackBar(
        val message: String ,
        val action : String?= null
    ): UiEvent()


}
