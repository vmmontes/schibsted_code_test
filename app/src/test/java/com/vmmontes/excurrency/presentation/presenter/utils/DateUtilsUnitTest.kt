package com.vmmontes.excurrency.presentation.presenter.utils

import com.vmmontes.excurrency.presentation.utils.getDateTime
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateUtilsUnitTest {
    private val dateInMiliseconds = 1553803626367
    private val dateInString = "28/03/2019"

    @Test
    fun validateCallSetViewForStartDateAndEndDateFromonViewCreated() {
        val parserDay = SimpleDateFormat("dd/MM/yyyy:HH:mm:ss", Locale.ENGLISH)
        val startDay = parserDay.parse("$dateInString:00:00:00").time
        val endDay = parserDay.parse("$dateInString:23:59:59").time

        val dateToValidate = getDateTime(dateInString, "dd/MM/yyyy")

        assertTrue(dateToValidate in startDay..endDay)
    }

    @Test
    fun verifyisValidDatesIsWorking() {
        assertTrue(getTimeDateInStringFormat(dateInMiliseconds, "dd/MM/yyyy") == dateInString)
    }
}
