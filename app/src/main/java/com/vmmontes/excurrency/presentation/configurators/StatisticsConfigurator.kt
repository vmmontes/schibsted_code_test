package com.vmmontes.excurrency.presentation.configurators

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.vmmontes.excurrency.R
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.presentation.utils.chart.DateAxisValueFormatter
import com.vmmontes.excurrency.presentation.utils.chart.StringAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class StatisticsConfigurator(val chart: LineChart,
                             val context: Context) {

    val entries = ArrayList<Entry>()
    var minAxisY : Float = 100.0f

    fun addEntryItem(dateXAxi: Date, yAxi: Float) {
        entries.add(Entry(java.lang.Long.valueOf(dateXAxi.time).toFloat(), yAxi))
    }

    fun addAll(list : List<HistoryDayDomainModel>) {
        for (historyDay in list) {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = format.parse(historyDay.day)
            val currency = historyDay.currenyEUR.toFloatOrNull()
            if (currency != null) {
                addEntryItem(date, currency)
                saveMinAxis(currency)
            }
        }
    }

    private fun saveMinAxis(num : Float) {
        if (num < minAxisY) {
            minAxisY = num
        }
    }

    private fun getDataSet(context: Context): LineDataSet {
        val dataSet = LineDataSet(entries, "")
        dataSet.isHighlightEnabled = false
        dataSet.color = ContextCompat.getColor(context, R.color.colorTextIcons)
        dataSet.setCircleColor(ContextCompat.getColor(context, R.color.colorTextIcons))
        dataSet.valueTextColor = ContextCompat.getColor(context, R.color.colorTextIcons)
        dataSet.setDrawValues(false)
        dataSet.setDrawCircles(false)

        return dataSet
    }

    private fun configureYAxis() {
        val leftAxis = chart.axisLeft
        leftAxis.axisMinimum = minAxisY
        leftAxis.valueFormatter = StringAxisValueFormatter()
        leftAxis.setDrawGridLines(false)
        leftAxis.textColor = ContextCompat.getColor(context, R.color.colorTextIcons)

        val rightAxis = chart.axisRight
        rightAxis.axisMinimum = minAxisY
        rightAxis.valueFormatter = StringAxisValueFormatter()
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawZeroLine(true)
        rightAxis.textColor = ContextCompat.getColor(context, R.color.colorTextIcons)
    }

    private fun configureXAxis() {
        val xAxis = chart.xAxis
        xAxis.granularity = 1f
        xAxis.valueFormatter = DateAxisValueFormatter()
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 4
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
        xAxis.textColor = ContextCompat.getColor(context, R.color.colorTextIcons)
    }

    private fun configureChart(lineData: LineData) {
        chart.legend.isEnabled = false
        chart.description.isEnabled = false
        chart.data = lineData
        chart.isScaleYEnabled = false

        chart.moveViewToX(chart.data.entryCount.toFloat())
    }

    fun inizializeStatistic() {
        val dataSet = getDataSet(context)
        val lineData = LineData(dataSet)

        configureYAxis()
        configureXAxis()
        configureChart(lineData)
    }

}