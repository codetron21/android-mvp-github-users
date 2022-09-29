package com.codetron.mvpgithubusers.base

import androidx.annotation.CallSuper
import com.codetron.mvpgithubusers.mvp.Presenter
import com.codetron.mvpgithubusers.mvp.ViewBinder

abstract class BasePresenter<V, VB : ViewBinder<V>> : Presenter<V, VB> {

    private var viewBinder: VB? = null

    @CallSuper
    override fun bind(viewBinder: VB) {
        this.viewBinder = viewBinder
    }

    @CallSuper
    override fun unBind() {
        viewBinder = null
    }

    protected fun useViewBinder(consumer: VB.() -> Unit) {
        viewBinder?.run(consumer)
    }

}