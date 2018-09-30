package com.homewise.android.ui.home

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.homewise.android.R
import com.homewise.android.data.models.TodoModel

class TodoListAdapter(val mList: MutableList<TodoModel>): RecyclerView.Adapter<TodoListAdapter.TodoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.raw_todo_list, parent, false);
        return TodoItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.tvTitle.text = mList[position].title
        holder.tvDescription.text = mList[position].description
        holder.tvTag.text = mList[position].tag
    }

    fun swapData(list: List<TodoModel>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewForeground = itemView!!.findViewById<ConstraintLayout>(R.id.view_foreground)
        val viewBackground = itemView!!.findViewById<ConstraintLayout>(R.id.view_background)
        val tvTitle = itemView!!.findViewById<TextView>(R.id.tv_title)
        val tvDescription = itemView!!.findViewById<TextView>(R.id.tv_description)
        val tvTag = itemView!!.findViewById<TextView>(R.id.tv_tag)
    }
}