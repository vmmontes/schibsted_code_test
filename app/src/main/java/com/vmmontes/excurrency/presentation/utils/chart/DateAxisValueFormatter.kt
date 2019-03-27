package com.vmmontes.excurrency.presentation.utils.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DateAxisValueFormatter : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        val pattern = "dd/MM/yy"
        val df = SimpleDateFormat(pattern)
        val date = Date(java.lang.Float.valueOf(value).toLong())

        return df.format(date)
    }
}