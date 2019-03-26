package com.vmmontes.excurrency.presentation.ui.graphic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.vmmontes.excurrency.R
import com.vmmontes.excurrency.presentation.utils.replaceFragment

class GraphicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(supportFragmentManager,
                GraphicFragment.newInstance()
            )
        }
    }

}
