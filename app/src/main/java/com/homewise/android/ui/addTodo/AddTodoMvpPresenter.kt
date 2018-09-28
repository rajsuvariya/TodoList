package com.homewise.android.ui.addTodo

import com.homewise.android.ui.base.MvpPresenter
import com.homewise.android.ui.base.MvpView

interface AddTodoMvpPresenter<T: MvpView>: MvpPresenter<T> {
    abstract fun addTodo(title: String, description: String, tag: String)
}