package com.droidper.xtrajob.core.extensions

import java.util.Locale

/**
 * Convierte entero a digitos con cero delante
 */
fun Int.withZero(): String {
    return String.format(Locale.getDefault(),"%02d", this)
}