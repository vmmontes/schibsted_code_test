package com.vmmontes.excurrency.presentation.ui.graphic

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vmmontes.excurrency.R
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.kernel.ui.BaseFragment
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter
import kotlinx.android.synthetic.main.fragment_graphic.*
import java.util.*
import javax.inject.Inject

class GraphicFragment : BaseFragment(), GraphicView, View.OnClickListener {

    @Inject lateinit var presenter : GraphicPresenter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getApplication().component.inject(this)
        presenter.onAttach(this)
        presenter.onViewCreated()
    }

    override fun onStart() {
        super.onStart()
        layoutStartDate.setOnClickListener(this)
        layoutEndDate.setOnClickListener(this)
        buttonSearch.setOnClickListener(this)

        lineChartHistory.setNoDataText(getString(R.string.select_date_range))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun showError() {
        lineChartHistory.clear()
        lineChartHistory.setNoDataText(getString(R.string.error_to_show_history))
    }

    override fun showValues(list : List<HistoryDayDomainModel>) {
        val statisticsConfigurator =
            GraphicChartConfigurator(lineChartHistory, context!!)
        statisticsConfigurator.addAll(list)
        statisticsConfigurator.inizializeStatistic()
    }

    override fun showValuesIsEmpty() {

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            layoutStartDate.id -> presenter.onStartDateClicked()
            layoutEndDate.id -> presenter.onEndDateClicked()
            buttonSearch.id -> presenter.onSearchButtonClicked()
        }
    }

    override fun openDataSelectorToStartDate() {
        openDateSelector(layoutStartDate.id)
    }

    override fun openDataSelectorToEndDate() {
        openDateSelector(layoutEndDate.id)
    }

    override fun showSelectedStartDate(date: String) {
        textViewStartDate.text = date
    }

    override fun showSelectedEndDate(date: String) {
        textViewEndDate.text = date
    }

    fun openDateSelector(id: Int) {
        val c = Calendar.getInstance()
        val currentYear = c.get(Calendar.YEAR)
        val currentMonth = c.get(Calendar.MONTH)
        val currentDay = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            when(id) {
                layoutStartDate.id -> presenter.starDateSelected(dayOfMonth.toString(), monthOfYear.toString(), year.toString())
                layoutEndDate.id -> presenter.endDateSelected(dayOfMonth.toString(), monthOfYear.toString(), year.toString())
            }
        }, currentYear, currentMonth, currentDay)

        dpd.show()
    }

    override fun showDatesError() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(getString(R.string.alert))
        builder.setMessage(getString(R.string.dates_error))
        builder.setPositiveButton(getString(R.string.ok)){ dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    override fun showLoading() {
        rotatingProgressBarView.visibility = View.VISIBLE
        lineChartHistory.visibility = View.GONE
    }

    override fun hideLoading() {
        rotatingProgressBarView.visibility = View.GONE
        lineChartHistory.visibility = View.VISIBLE
    }
}
