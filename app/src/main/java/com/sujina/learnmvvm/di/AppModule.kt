package com.sujina.learnmvvm.di

import android.app.Application
import androidx.room.Room
import com.sujina.learnmvvm.data.TaskDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDB(
        app: Application,
    callBack: TaskDB.Callback
    ) =
        Room.databaseBuilder(app, TaskDB::class.java, "task_db")
            .fallbackToDestructiveMigration()
            .addCallback(callBack)
            .build()
    @Provides
    fun provideTaskDao(db: TaskDB) =
        db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope