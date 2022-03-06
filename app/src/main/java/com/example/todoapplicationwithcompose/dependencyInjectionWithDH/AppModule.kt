package com.example.todoapplicationwithcompose.dependencyInjectionWithDH

import android.app.Application
import androidx.room.Room
import com.example.todoapplicationwithcompose.TodoRepo
import com.example.todoapplicationwithcompose.data.TodoDatabase
import com.example.todoapplicationwithcompose.data.TodoRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app:Application):TodoDatabase{
        return Room.databaseBuilder(
            app ,
            TodoDatabase::class.java,
        "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDatabase): TodoRepoInterface{
        return TodoRepo(db.dao)

    }


}