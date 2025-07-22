package com.droidper.xtrajob.core.exception

sealed class Failure {
    companion object {
        private const val CODE_UNKNOWN_ERROR = -100
        private const val MESSAGE_UNKNOWN_ERROR = "unknown_error"
        fun getMessage (failure: Failure?): String? {
            return when (failure) {
                is NetworkConnection -> failure.errorMessage
                is ServerError -> failure.errorMessage
                is CustomError -> failure.errorMessage
                else -> MESSAGE_UNKNOWN_ERROR
            }
        }
        fun getCode (failure: Failure?): Int {
            return when (failure) {
                is NetworkConnection -> failure.errorCode
                is ServerError -> failure.errorCode
                is CustomError -> failure.errorCode
                else -> CODE_UNKNOWN_ERROR
            }
        }
    }
    data class NetworkConnection(val errorCode: Int , val errorMessage: String? = null) : Failure(){
        companion object {
            const val CODE_NOT_NETWORK = -1
            const val MESSAGE_NOT_NETWORK = "not_network"
        }
    }
    data class ServerError(val errorCode: Int, val errorMessage: String? = null) : Failure(){
        companion object {
            const val CODE_UNAUTHORIZED = 401
            const val CODE_BAD_REQUEST = 400
        }
    }
    data class CustomError(val errorCode: Int, val errorMessage: String? = null) : Failure(){
        companion object {
            const val CODE_CONVERT_JSON = 11
            const val MESSAGE_CONVERT_JSON = "JsonSyntaxException_error"
            const val CODE_EVENT_SOCKET_ERROR = 12
        }
    }


}