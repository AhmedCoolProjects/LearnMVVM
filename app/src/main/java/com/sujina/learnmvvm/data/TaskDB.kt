package com.sujina.learnmvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sujina.learnmvvm.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDB : RoomDatabase(){
    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database : Provider<TaskDB>,
        @ApplicationScope private val applicationScope : CoroutineScope

        ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do true", important = true))
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do yes",completed = true))
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do"))
                dao.insert(Task("My first to do yes 2",completed = true))
            }

        }
    }
}