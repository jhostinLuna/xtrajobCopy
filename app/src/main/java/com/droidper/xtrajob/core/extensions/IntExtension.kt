package com.droidper.xtrajob.core.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

/**
 * Convierte entero a digitos con cero delante
 */
fun Int.withZero(): String {
    return String.format(Locale.getDefault(),"%02d", this)
}
fun Long.timeMillisToLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}