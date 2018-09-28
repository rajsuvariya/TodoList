package com.homewise.android.ui.addTodo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homewise.android.R
import com.homewise.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject

class AddTodoActivity : BaseActivity(), AddTodoMvpView {

    @Inject
    lateinit var mPresenter : AddTodoMvpPresenter<AddTodoMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_add_todo, frame_layout_base, true)

        setToolbarTitle(getString(R.string.add_new_todo_activity_title))
        setActionBarWithBackButton()

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        fab_add_todo.setOnClickListener {
            mPresenter.addTodo(til_title.editText!!.text.toString(), til_description.editText!!.text.toString(), til_tag.editText!!.text.toString())
        }
    }

    override fun closeActivity() {
        this.finish()
    }
}
