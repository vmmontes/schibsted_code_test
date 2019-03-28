package com.vmmontes.excurrency.presentation.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.vmmontes.excurrency.R

fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.view_frame, fragment, fragment::class.java.name)
        .commit()
}