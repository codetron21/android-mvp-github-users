package com.codetron.mvpgithubusers.di

import com.codetron.mvpgithubusers.di.Keys.LIST_USERS_PRESENTER
import com.codetron.mvpgithubusers.di.Keys.LIST_USERS_VIEW_BINDER
import com.codetron.mvpgithubusers.di.Keys.USERS_SERVICE
import com.codetron.mvpgithubusers.di.Keys.USERS_USE_CASE
import com.codetron.mvpgithubusers.features.ListUsersPresenter
import com.codetron.mvpgithubusers.features.ListUsersPresenterImpl
import com.codetron.mvpgithubusers.features.ListUsersViewBinder
import com.codetron.mvpgithubusers.features.ListUsersViewBinderImpl
import com.codetron.mvpgithubusers.usecase.GetUsersUseCase

val activityServiceLocatorFactory = { serviceLocator: ServiceLocator ->
    ActivityServiceLocatorImpl().apply {
        applicationServiceLocator = serviceLocator
    }
}

class ActivityServiceLocatorImpl : ServiceLocator {

    var applicationServiceLocator: ServiceLocator? = null

    var getUsersUseCase: GetUsersUseCase? = null
        private set

    var listUsersPresenter: ListUsersPresenter? = null
        private set

    var listUsersViewBinder: ListUsersViewBinder? = null
        private set

    @Suppress("UNCHECKED_CAST")
    override fun <D> lookUp(name: String): D {
        return when (name) {
            USERS_USE_CASE -> {
                if (getUsersUseCase == null) {
                    getUsersUseCase =
                        GetUsersUseCase(applicationServiceLocator!!.lookUp(USERS_SERVICE))
                }

                getUsersUseCase as D
            }
            LIST_USERS_VIEW_BINDER -> {
                if (listUsersViewBinder == null) {
                    listUsersViewBinder = ListUsersViewBinderImpl(lookUp(LIST_USERS_PRESENTER))
                }

                listUsersViewBinder as D
            }
            LIST_USERS_PRESENTER -> {
                if (listUsersPresenter == null) {
                    listUsersPresenter = ListUsersPresenterImpl(lookUp(USERS_USE_CASE))
                }

                listUsersPresenter as D
            }
            else -> {
                applicationServiceLocator?.lookUp(name)
                    ?: throw IllegalStateException("Dependency can't be found with name: $name")
            }
        }
    }

}