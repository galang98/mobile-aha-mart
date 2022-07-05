package com.aha.mart.app.data.network

import com.aha.mart.app.data.network.model.request.LoginOtpRequest
import com.aha.mart.app.data.network.model.request.VerifyOtpRequest
import com.aha.mart.app.data.network.model.response.ApiResponse
import com.aha.mart.app.data.network.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEndPoint {
    @POST("v2/sessions/login_otp.json")
    suspend fun loginOTP(
        @Body loginOtpRequest: LoginOtpRequest
    ) : ApiResponse
    @POST("v2/sessions/verify_otp_login.json")
    suspend fun verifyOTP(
        @Body verifyOtpRequest: VerifyOtpRequest
    ) : UserResponse
}