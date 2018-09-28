package com.homewise.android.ui.addTodo

import com.homewise.android.data.DataManager
import com.homewise.android.ui.base.BasePresenter
import com.homewise.android.ui.base.MvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddTodoPresenter<T : AddTodoMvpView> @Inject constructor(dataManager: DataManager, val mCompositeDisposable: CompositeDisposable) : BasePresenter<T>(dataManager, mCompositeDisposable), AddTodoMvpPresenter<T> {
    override fun addTodo(title: String, description: String, tag: String) {
        var valid = true
        if (title.isNullOrEmpty()) {
            valid = false
        }
        if (description.isNullOrEmpty()) {
            valid = false
        }
        if (tag.isNullOrEmpty()) {
            valid = false
        }
        if (valid) {
            mCompositeDisposable.add(
                    getDataManager().addTodo(title, description, tag)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ r ->
                                getMvpView().closeActivity()
                            }, { t ->
                                t.printStackTrace()
                            })
            )
        }
    }
}