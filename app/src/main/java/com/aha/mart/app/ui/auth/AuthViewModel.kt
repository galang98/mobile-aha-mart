package com.aha.mart.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aha.mart.app.data.network.Resource
import com.aha.mart.app.data.network.model.request.LoginOtpRequest
import com.aha.mart.app.data.network.model.request.VerifyOtpRequest
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.data.network.model.response.ApiResponse
import com.aha.mart.app.data.network.model.response.UserResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel(){

    private val _loginResponse : MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<ApiResponse>> get() = _loginResponse
    private val _verifyResponse : MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val verifyResponse: LiveData<Resource<UserResponse>> get() = _verifyResponse

    fun login(
        phone : String,
        isWhatsapp : Boolean
    ) = viewModelScope.launch {
        val request = LoginOtpRequest()
        request.login = phone
        request.whatsapp = isWhatsapp
        _loginResponse.value = repository.loginOtp(request)
    }

    fun verifyOtp(
        phone: String,
        code: String
    ) = viewModelScope.launch {
        val request = VerifyOtpRequest()
        request.phoneNumber = phone
        request.code = code
        _verifyResponse.value = repository.verifyOtp(request)
    }

}