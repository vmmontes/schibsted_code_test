package com.vmmontes.excurrency.di

import android.content.Context
import com.vmmontes.excurrency.ExCurrencyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val exCurrencyApplication : ExCurrencyApplication) {
    @Provides
    @Singleton
    fun provideContext() : Context = exCurrencyApplication.applicationContext
}