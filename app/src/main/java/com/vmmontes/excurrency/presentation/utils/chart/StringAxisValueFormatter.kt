package com.vmmontes.excurrency.presentation.utils.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.math.BigDecimal

class StringAxisValueFormatter : IAxisValueFormatter {
    val COIN = "€"

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        val decimal = BigDecimal((value % 1).toDouble())

        return if (decimal.toFloat() == 0f) {
            String.format("%.0f", value) + COIN
        } else {
            String.format("%.2f", value) + COIN
        }
    }
}