package com.codetron.mvpgithubusers

import android.app.Activity
import android.app.Application
import com.codetron.mvpgithubusers.di.ApplicationServiceLocatorImpl
import com.codetron.mvpgithubusers.di.ServiceLocator

class GitHubUsersApp : Application() {

    val appServiceLocator: ServiceLocator by lazy {
        ApplicationServiceLocatorImpl()
    }

}

fun <A : Any> Activity.lookUp(name: String): A =
    (applicationContext as GitHubUsersApp).appServiceLocator.lookUp(name)