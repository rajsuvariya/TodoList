package com.homewise.android.data.local.database

import android.arch.persistence.room.*
import com.homewise.android.data.models.TodoModel
import io.reactivex.Single

@Dao
interface TodoDao {

    @get:Query("SELECT * FROM todomodel")
    val all: Single<List<TodoModel>>

    @Query("SELECT * FROM todomodel WHERE tag LIKE :tag")
    fun findByTag(tag: String): Single<List<TodoModel>>

    @Insert
    fun insert(todoItem: TodoModel)

    @Update
    fun update(todoItem: TodoModel)

    @Delete
    fun delete(todoItem: TodoModel)
}