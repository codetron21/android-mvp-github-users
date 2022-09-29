package com.codetron.mvpgithubusers.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("login") val username: String? = null,
    @SerializedName("avatar_url") val photoUrl: String? = null,
)