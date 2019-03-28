package com.vmmontes.excurrency.di

import com.vmmontes.excurrency.ExCurrencyApplication
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, GraphicModule::class])
interface ApplicationComponent {
    fun inject(app: ExCurrencyApplication)
    fun inject(graphicView: GraphicFragment)
}