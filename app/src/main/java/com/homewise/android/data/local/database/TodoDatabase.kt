package com.homewise.android.data.local.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.homewise.android.data.models.TodoModel
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = arrayOf(TodoModel::class), version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun productDao(): TodoDao

    companion object {
        private var sInstance: TodoDatabase? = null
        private val LOCK = Any()
        private val DB_NAME = "todo_databse"

        fun getInstance(context: Context): TodoDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context,
                                TodoDatabase::class.java, DB_NAME)
                                .build()
                    }
                }
            }
            return sInstance!!
        }

    }
}