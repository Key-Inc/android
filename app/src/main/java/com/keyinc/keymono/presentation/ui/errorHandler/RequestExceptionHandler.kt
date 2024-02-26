package com.keyinc.keymono.presentation.ui.errorHandler

import com.keyinc.keymono.presentation.ui.util.NetworkErrorCodes
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

class RequestExceptionHandler(
    private inline val onUnauthorizedException: () -> Unit,
    private inline val onBaseException: () -> Unit
) {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                NetworkErrorCodes.UNAUTHORIZED -> onUnauthorizedException
                else -> onBaseException
            }

            else -> onBaseException

        }
    }
}