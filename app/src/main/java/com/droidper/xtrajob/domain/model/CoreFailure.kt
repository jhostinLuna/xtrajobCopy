package com.droidper.xtrajob.domain.model

sealed class CoreFailure {
    data class DatabaseError(val code: Int = -1, val message: String? = "") : CoreFailure()
    //data class ServerError(val code: Int, val message: String? = null) : CoreFailure()
    //data class Unknown(val code: Int = -100,val message: String? = "An unknown error occurred") : CoreFailure()
}