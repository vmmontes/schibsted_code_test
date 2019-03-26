package com.vmmontes.excurrency.presentation.presenter.graphic

import com.vmmontes.excurrency.kernel.coroutines.backgroundContext
import com.vmmontes.excurrency.kernel.presenter.CoroutinesPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GraphicPresenter : CoroutinesPresenter<GraphicView>() {

    fun onViewCreate() {
        launch {
            async(backgroundContext) {

            }.await().run {

            }
        }
    }
}