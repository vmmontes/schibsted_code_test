package com.vmmontes.excurrency.presentation.ui.graphic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmmontes.excurrency.R
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter

class GraphicFragment : Fragment(), GraphicView {

    val presenter = GraphicPresenter()

    companion object {
        @JvmStatic
        fun newInstance() = GraphicFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graphic, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
        presenter.onViewCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
