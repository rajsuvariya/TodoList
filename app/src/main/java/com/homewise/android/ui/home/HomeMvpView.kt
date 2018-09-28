package com.homewise.android.ui.home

import com.homewise.android.data.models.TodoModel
import com.homewise.android.ui.base.MvpView

/**
 * Created by @raj on 27/12/17.
 */
interface HomeMvpView : MvpView {
    fun updateTodoList(list: List<TodoModel>)
    fun showNoTodoMessage()
}