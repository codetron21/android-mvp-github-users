package com.codetron.mvpgithubusers.network

import com.codetron.mvpgithubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GitHubUsersService {

    @Headers("Accept: application/vnd.github+json")
    @GET("/users")
    fun getUsers(): Call<List<User>>

}