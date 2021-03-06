package com.homewise.android

import android.app.Application
import android.content.Context
import com.homewise.android.injection.component.ApplicationComponent
import com.homewise.android.injection.component.DaggerApplicationComponent
import com.homewise.android.injection.module.ApplicationModule

/**
 * Created by @raj on 26/12/17.
 */
class HomeWiseApplication : Application() {

    private val mApplicationComponent : ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent.inject(this)
    }

    fun getApplictionComponent(): ApplicationComponent {
        return mApplicationComponent
    }

    companion object {
        fun get(context: Context) : HomeWiseApplication{
            return context.applicationContext as HomeWiseApplication
        }
    }
}