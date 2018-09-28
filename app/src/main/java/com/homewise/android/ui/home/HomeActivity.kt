package com.homewise.android.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.homewise.android.R
import com.homewise.android.data.models.TodoModel
import com.homewise.android.ui.addTodo.AddTodoActivity
import com.homewise.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeMvpView, RecyclerviewItemTouchListener.RecyclerItemTouchHelperListener {

    @Inject
    lateinit var mPresenter: HomeMvpPresenter<HomeMvpView>

    private lateinit var adapter: TodoListAdapter
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_home, frame_layout_base, true)

        setToolbarTitle(getString(R.string.todo_list_activity_title))

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)
        mPresenter.initView()

        adapter = TodoListAdapter(mutableListOf())
        rv_todo_list.adapter = adapter
        rv_todo_list.layoutManager = LinearLayoutManager(this)

        val itemTouchHelperCallback = RecyclerviewItemTouchListener(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_todo_list)

        fab_add_todo.setOnClickListener {
            openAddTodoListActivity()
        }
    }

    private fun openAddTodoListActivity() {
        val intent = Intent(this, AddTodoActivity::class.java);
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.initView()
    }

    override fun updateTodoList(list: List<TodoModel>) {
        tv_no_todo.visibility = View.GONE
        adapter.swapData(list)
    }

    override fun showNoTodoMessage() {
        tv_no_todo.visibility = View.VISIBLE
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        mPresenter.deleteTodo(adapter.mList[position])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mSearchView = menu!!.findItem(R.id.search).actionView as SearchView
        mSearchView.maxWidth = Integer.MAX_VALUE
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.searchTodos(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.searchTodos(newText)
                return false
            }
        })
        return true
    }
}
