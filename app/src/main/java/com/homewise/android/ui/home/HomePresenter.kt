package com.homewise.android.ui.home

import android.util.Log
import com.homewise.android.data.DataManager
import com.homewise.android.data.models.TodoModel
import com.homewise.android.ui.base.BasePresenter
import com.homewise.android.ui.base.MvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by @raj on 27/12/17.
 */
class HomePresenter<T : HomeMvpView> @Inject constructor(dataManager: DataManager, val compositeDisposable: CompositeDisposable) : BasePresenter<T>(dataManager, compositeDisposable), HomeMvpPresenter<T> {
    override fun initView() {
        compositeDisposable.add(
                getDataManager().getTodoList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { r ->
                            getMvpView().updateTodoList(r)
                            if (r.isEmpty()) {
                                getMvpView().showNoTodoMessage()
                            }
                        }
        )
    }

    override fun deleteTodo(todo: TodoModel) {
        compositeDisposable.add(
                getDataManager().deleteTodo(todo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap {
                            return@flatMap getDataManager().getTodoList()
                                    .subscribeOn(Schedulers.io())
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ r ->
                            getMvpView().updateTodoList(r)
                            if (r.isEmpty()) {
                                getMvpView().showNoTodoMessage()
                            }
                        }, { t ->
                            t.printStackTrace()
                        }))
    }

    override fun searchTodos(query: String) {
        if (query.isNullOrEmpty()) {
            initView()
        } else {
            compositeDisposable.add(
                    getDataManager().getFilteredTodoList(query)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { r ->
                                getMvpView().updateTodoList(r)
                                if (r.isEmpty()) {
                                    getMvpView().showNoTodoMessage()
                                }
                            }
            )
        }

    }
}