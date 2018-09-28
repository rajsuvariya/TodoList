package com.homewise.android.injection.module;

import android.app.Activity;
import android.content.Context;
import com.homewise.android.injection.ActivityContext
import com.homewise.android.ui.home.HomeMvpPresenter
import com.homewise.android.ui.home.HomeMvpView
import com.homewise.android.ui.home.HomePresenter

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable
import com.homewise.android.injection.PerActivity
import com.homewise.android.ui.addTodo.AddTodoMvpPresenter
import com.homewise.android.ui.addTodo.AddTodoMvpView
import com.homewise.android.ui.addTodo.AddTodoPresenter


/**
 * Created by Shashank on 28/09/17.
 */
@Module
class ActivityModule(activity: Activity) {

    private var mActivity: Activity = activity

    @Provides
    @ActivityContext
    internal fun provideActivityContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @PerActivity
    fun provideHomePresenter(presenter: HomePresenter<HomeMvpView>): HomeMvpPresenter<HomeMvpView> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideAddTodoPresenter(presenter: AddTodoPresenter<AddTodoMvpView>): AddTodoMvpPresenter<AddTodoMvpView> {
        return presenter
    }
}