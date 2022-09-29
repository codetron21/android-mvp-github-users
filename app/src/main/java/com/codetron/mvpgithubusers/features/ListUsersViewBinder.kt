package com.codetron.mvpgithubusers.features

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codetron.mvpgithubusers.R
import com.codetron.mvpgithubusers.model.User
import com.codetron.mvpgithubusers.mvp.ViewBinder

interface ListUsersViewBinder : ViewBinder<View> {

    fun displayUsers(users: List<User>)
    fun displayError()
    fun showLoading()
    fun hideLoading()
    fun showUserSelected(username: String)

    interface UsersItemSelectedListener {
        fun select(user: User)
    }

}

class ListUsersViewBinderImpl(
    private val usersItemSelectedListener: ListUsersViewBinder.UsersItemSelectedListener? = null
) : ListUsersViewBinder {

    private lateinit var listUsers: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var textError: TextView

    private lateinit var adapter: ListUsersAdapter

    override fun init(rootView: View) {
        listUsers = rootView.findViewById(R.id.list_users)
        loading = rootView.findViewById(R.id.loading)
        textError = rootView.findViewById(R.id.text_error)

        adapter = ListUsersAdapter {
            usersItemSelectedListener?.select(it)
        }

        setupRecyclerView(listUsers)
    }

    override fun displayUsers(users: List<User>) {
        listUsers.isVisible = users.isNotEmpty()
        textError.isVisible = users.isEmpty()

        if (users.isEmpty()) {
            textError.text = "Empty"
        } else {
            adapter.bindUsers(users)
        }
    }

    override fun displayError() {
        textError.isVisible = true
        textError.text = "Error"
    }

    override fun showLoading() {
        loading.isVisible = true
    }

    override fun hideLoading() {
        loading.isVisible = false
    }

    override fun showUserSelected(username: String) {
        Toast.makeText(listUsers.context, username, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(recyclerView.context)
            adapter = this@ListUsersViewBinderImpl.adapter
        }
    }
}