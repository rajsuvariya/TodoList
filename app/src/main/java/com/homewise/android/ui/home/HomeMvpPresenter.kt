package com.homewise.android.ui.home

import com.homewise.android.data.models.TodoModel
import com.homewise.android.ui.base.MvpPresenter
import com.homewise.android.ui.base.MvpView

interface HomeMvpPresenter<T: MvpView>: MvpPresenter<T> {
    fun initView()
    fun deleteTodo(todo: TodoModel)
    fun searchTodos(query: String)
}