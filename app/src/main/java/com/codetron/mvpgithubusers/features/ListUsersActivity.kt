package com.codetron.mvpgithubusers.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.mvpgithubusers.R

class ListUsersActivity : AppCompatActivity() {

    lateinit var listUsersViewBinder: ListUsersViewBinder
    lateinit var listUsersPresenter: ListUsersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ListUsersActivityInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_list_users)

        listUsersViewBinder.init(findViewById(android.R.id.content))
    }

    override fun onStart() {
        super.onStart()
        with(listUsersPresenter) {
            bind(listUsersViewBinder)
            fetchData()
        }
    }

    override fun onStop() {
        super.onStop()
        with(listUsersPresenter) {
            unBind()
        }
    }

}