package com.vmmontes.excurrency.kernel.presenter

abstract class Presenter<T> {
    protected var view: T? = null

    open fun onAttach(view: T) {
        this.view = view
    }

    open fun onDetach() {
        view = null
    }
}