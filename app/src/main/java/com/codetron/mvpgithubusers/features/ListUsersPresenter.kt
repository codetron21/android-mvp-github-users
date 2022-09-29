package com.codetron.mvpgithubusers.features

import android.view.View
import com.codetron.mvpgithubusers.base.BasePresenter
import com.codetron.mvpgithubusers.model.User
import com.codetron.mvpgithubusers.mvp.Presenter
import com.codetron.mvpgithubusers.usecase.GetUsersUseCase

interface ListUsersPresenter :
    Presenter<View, ListUsersViewBinder>, ListUsersViewBinder.UsersItemSelectedListener {

    fun fetchData()

}

class ListUsersPresenterImpl(
    private val getUsersUseCase: GetUsersUseCase
) : BasePresenter<View, ListUsersViewBinder>(), ListUsersPresenter {

    override fun fetchData() {
        useViewBinder {
            showLoading()
            getUsersUseCase.execute(
                onSuccess = {
                    hideLoading()
                    displayUsers(it)
                },
                onError = {
                    hideLoading()
                    displayError()
                }
            )
        }
    }

    override fun select(user: User) {
        useViewBinder {
            user.username?.let { showUserSelected(it) }
        }
    }
}