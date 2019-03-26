package com.vmmontes.excurrency.presentation.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.vmmontes.excurrency.R

fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.view_frame, fragment, fragment::class.java.name)
        .addToBackStack(fragment::class.java.name)
        .commit()
}

fun addFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(R.id.view_frame, fragment, fragment::class.java.name)
        .addToBackStack(fragment::class.java.name)
        .commit()
}

fun removeCurrentFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .disallowAddToBackStack()
            .commit()

        supportFragmentManager.popBackStack()
    }
}