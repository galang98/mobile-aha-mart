package com.aha.mart.app.data.network.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginOtpRequest {
    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("is_whatsapp")
    @Expose
    var whatsapp = false
}