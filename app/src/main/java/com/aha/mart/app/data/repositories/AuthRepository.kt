package com.aha.mart.app.data.repositories

import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.network.model.request.LoginOtpRequest
import com.aha.mart.app.data.network.model.request.VerifyOtpRequest

class AuthRepository (
    private val apiEndPoint : ApiEndPoint
) : BaseRepository() {

    suspend fun loginOtp(
        request: LoginOtpRequest
    ) = safeApiCall {
        apiEndPoint.loginOTP(request)
    }

    suspend fun verifyOtp(
        request: VerifyOtpRequest
    ) = safeApiCall {
        apiEndPoint.verifyOTP(request)
    }
}