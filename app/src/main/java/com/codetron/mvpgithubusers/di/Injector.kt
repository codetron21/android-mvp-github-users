package com.codetron.mvpgithubusers.di

interface Injector<A> {

    fun inject(target: A)

}