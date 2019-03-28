package com.vmmontes.excurrency.kernel.ui

import android.support.v4.app.Fragment
import com.vmmontes.excurrency.ExCurrencyApplication

abstract class BaseFragment : Fragment() {

    fun getApplication() : ExCurrencyApplication {
        return activity!!.application as ExCurrencyApplication
    }
}