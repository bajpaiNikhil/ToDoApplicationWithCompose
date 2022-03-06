package com.example.todoapplicationwithcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase(){

    abstract val dao: TodoDao

}