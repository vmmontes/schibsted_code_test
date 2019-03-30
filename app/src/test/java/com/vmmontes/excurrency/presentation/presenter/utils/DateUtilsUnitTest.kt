package com.vmmontes.excurrency.presentation.presenter.utils

import com.vmmontes.excurrency.presentation.utils.getDateTime
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateUtilsUnitTest {

    companion object {
        private val FAKE_DATE_IN_MILISECONDS = 1553803626367
        private val FAKE_STRING_DATE = "28/03/2019"
    }

    @Test
    fun whenItGetDataInMilisecondsThenstartDateShouldBeBeetwenStartDayAndEndDayDate() {
        val parserDay = SimpleDateFormat("dd/MM/yyyy:HH:mm:ss", Locale.ENGLISH)
        val startDay = parserDay.parse("$FAKE_STRING_DATE:00:00:00").time
        val endDay = parserDay.parse("$FAKE_STRING_DATE:23:59:59").time

        val dateToValidate = getDateTime(FAKE_STRING_DATE, "dd/MM/yyyy")

        assertTrue(dateToValidate in startDay..endDay)
    }

    @Test
    fun verifyIfItGetsRightDate() {
        assertTrue(getTimeDateInStringFormat(FAKE_DATE_IN_MILISECONDS, "dd/MM/yyyy") == FAKE_STRING_DATE)
    }
}
