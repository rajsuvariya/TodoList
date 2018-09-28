package com.homewise.android.data

import com.homewise.android.data.local.sharedPref.PreferenceManager
import com.homewise.android.data.models.TodoModel
import com.homewise.android.data.remote.ApiManager
import io.reactivex.Single

/**
 * Created by @raj on 26/12/17.
 */
interface DataManager : ApiManager, PreferenceManager {
    fun addTodo(title: String, description: String, tag: String): Single<Unit>
    fun getTodoList(): Single<List<TodoModel>>
    fun deleteTodo(todo: TodoModel): Single<Unit>
}