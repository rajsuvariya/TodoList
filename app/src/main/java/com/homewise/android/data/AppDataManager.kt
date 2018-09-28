package com.homewise.android.data

import android.os.Parcel
import android.os.Parcelable
import com.homewise.android.data.local.database.TodoDatabase
import com.homewise.android.data.local.sharedPref.PreferenceManager
import com.homewise.android.data.models.TodoModel
import com.homewise.android.data.remote.ApiManager
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by @raj on 26/12/17.
 */
class AppDataManager @Inject constructor(preferenceManager: PreferenceManager, apiManager: ApiManager) : DataManager {
    private val mPreferenceManager: PreferenceManager = preferenceManager
    private val mApiManager: ApiManager = apiManager
    @Inject
    lateinit var mDatabase: TodoDatabase

    override fun setWelcomeMessage(msg: String) {
        mPreferenceManager.setWelcomeMessage(msg)
    }

    override fun getWelcomeMessage(): String? {
        return mPreferenceManager.getWelcomeMessage()
    }

    override fun addTodo(title: String, description: String, tag: String): Single<Unit> {
        val todoModel = TodoModel()
        todoModel.title = title
        todoModel.description = description
        todoModel.tag = tag

        return Single.fromCallable {
            mDatabase.productDao().insert(todoModel)
        }

    }

    override fun deleteTodo(todo: TodoModel): Single<Unit> {
        return Single.fromCallable {
            mDatabase.productDao().delete(todo)
        }
    }


    override fun getTodoList(): Single<List<TodoModel>> {
        return mDatabase.productDao().all
    }

    override fun getFilteredTodoList(query: String): Single<List<TodoModel>> {
        return mDatabase.productDao().findByTag(query)
    }
}