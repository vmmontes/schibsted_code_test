package com.vmmontes.excurrency

import android.app.Application
import com.vmmontes.excurrency.di.ApplicationComponent
import com.vmmontes.excurrency.di.ApplicationModule
import com.vmmontes.excurrency.di.DaggerApplicationComponent
import com.vmmontes.excurrency.di.GraphicModule

class ExCurrencyApplication : Application() {

    val component : ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .graphicModule(GraphicModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}