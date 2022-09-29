package com.codetron.mvpgithubusers.usecase

import com.codetron.mvpgithubusers.model.User
import com.codetron.mvpgithubusers.network.GitHubUsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUsersUseCase(
    private val usersService: GitHubUsersService
) {
    fun execute(onSuccess: (List<User>) -> Unit, onError: () -> Unit) {
        usersService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                onSuccess(response.body() ?: emptyList())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                onError()
            }
        })
    }
}