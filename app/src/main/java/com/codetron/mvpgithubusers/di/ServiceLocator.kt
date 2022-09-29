package com.codetron.mvpgithubusers.di

interface ServiceLocator {

    fun <D : Any?> lookUp(name: String): D

}