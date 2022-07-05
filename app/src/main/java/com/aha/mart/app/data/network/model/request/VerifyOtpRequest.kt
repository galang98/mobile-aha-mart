package com.aha.mart.app.data.network.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyOtpRequest {
    @SerializedName("phone_number")
    @Expose
    var phoneNumber : String? = null

    @SerializedName("code")
    @Expose
    var code : String? = null
}