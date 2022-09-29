package com.codetron.mvpgithubusers.features

import com.codetron.mvpgithubusers.di.Injector
import com.codetron.mvpgithubusers.di.Keys.ACTIVITY_LOCATOR_FACTORY
import com.codetron.mvpgithubusers.di.Keys.LIST_USERS_PRESENTER
import com.codetron.mvpgithubusers.di.Keys.LIST_USERS_VIEW_BINDER
import com.codetron.mvpgithubusers.di.ServiceLocator
import com.codetron.mvpgithubusers.lookUp

object ListUsersActivityInjector : Injector<ListUsersActivity> {

    override fun inject(target: ListUsersActivity) {
        val activityServiceLocator = target.lookUp<ServiceLocator>(ACTIVITY_LOCATOR_FACTORY)
        with(target) {
            listUsersViewBinder = activityServiceLocator.lookUp(LIST_USERS_VIEW_BINDER)
            listUsersPresenter = activityServiceLocator.lookUp(LIST_USERS_PRESENTER)
        }
    }
}
