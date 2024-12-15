package com.droidper.xtrajob.core.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formattedSpanish(): String{
    return this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}