package com.codetron.mvpgithubusers.di

import com.codetron.mvpgithubusers.di.Keys.ACTIVITY_LOCATOR_FACTORY
import com.codetron.mvpgithubusers.di.Keys.USERS_SERVICE
import com.codetron.mvpgithubusers.network.GitHubUsersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationServiceLocatorImpl : ServiceLocator {

    private val usersService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubUsersService::class.java)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <D> lookUp(name: String): D {
        return when (name) {
            USERS_SERVICE -> usersService
            ACTIVITY_LOCATOR_FACTORY -> activityServiceLocatorFactory(this)
            else -> throw IllegalStateException("Dependency can't be found with name: $name")
        } as D
    }

}