package com.vmmontes.excurrency.presentation.ui.graphic

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.vmmontes.excurrency.R
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.presentation.configurators.StatisticsConfigurator
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter
import kotlinx.android.synthetic.main.fragment_graphic.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class GraphicFragment : Fragment(), GraphicView, View.OnClickListener {

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

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this)
        layoutStartDate.setOnClickListener(this)
        layoutEndDate.setOnClickListener(this)
        buttonSearch.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun showError() {
        Toast.makeText(context!!,"Ha fallado", Toast.LENGTH_LONG).show()
    }

    override fun showValues(list : List<HistoryDayDomainModel>) {
        val statisticsConfigurator = StatisticsConfigurator(lineChartHistory, context!!)
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

        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
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
}
