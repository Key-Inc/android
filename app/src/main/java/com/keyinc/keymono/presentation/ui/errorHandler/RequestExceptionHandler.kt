package com.keyinc.keymono.presentation.ui.errorHandler

import com.keyinc.keymono.presentation.ui.util.NetworkErrorCodes
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

class RequestExceptionHandler(
    private inline val onUnauthorizedException: () -> Unit,
    private inline val onBadRegistrationRequest: () -> Unit,
    private inline val onBaseException: () -> Unit
) {

    private companion object {
        const val BAD_REGISTRATION_REQUEST_MARK = "User already exists"
    }

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                NetworkErrorCodes.UNAUTHORIZED -> onUnauthorizedException
                NetworkErrorCodes.BAD_REQUEST -> {
                    if (exception.message()
                            .contains(BAD_REGISTRATION_REQUEST_MARK)
                    ) onBadRegistrationRequest
                    else onBaseException
                }

                else -> onBaseException
            }

            else -> onBaseException

        }
    }
}