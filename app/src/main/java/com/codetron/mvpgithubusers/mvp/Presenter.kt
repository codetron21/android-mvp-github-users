package com.codetron.mvpgithubusers.mvp

interface Presenter<V,VB:ViewBinder<V>> {

    fun bind(viewBinder: VB)

    fun unBind()

}