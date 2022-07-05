package com.aha.mart.app.data.network.model.response

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("customer")
    val customer: Customer? = null

    @SerializedName("token")
    private val token: String? = null

    @SerializedName("unconfirmed_email")
    private val unconfirmedEmail = false

    @SerializedName("email_confirmed")
    private val emailConfirmed = false

    @SerializedName("phone_number_confirmed")
    private val phoneNumberConfirmed = false

    @SerializedName("password_configured")
    private val passeordConfigured = false

    @SerializedName("countdown")
    private val countdown = 0

    class Customer {

        @SerializedName("security_question_answer")
        private val securityQuestionAnswer: String? = null

        @SerializedName("gender")
        private val gender = 0

        @SerializedName("created_at")
        private val createdAt: String? = null

        @SerializedName("security_question_id")
        private val securityQuestionId = 0

        @SerializedName("identity_number")
        private val identityNumber: String? = null

        @SerializedName("uid")
        private val uid: String? = null

        @SerializedName("notification")
        private val notification = false

        @SerializedName("discarded_at")
        private val discardedAt: String? = null

        @SerializedName("updated_at")
        private val updatedAt: String? = null

        @SerializedName("provider")
        private val provider: String? = null

        @SerializedName("dob")
        private val dob: String? = null

        @SerializedName("name")
        val name: String? = null

        @SerializedName("phone_number")
        private val phoneNumber: String? = null

        @SerializedName("id")
        private val id = 0

        @SerializedName("email")
        private val email: String? = null

        @SerializedName("username")
        private val username: String? = null

        @SerializedName("phone_code")
        private val phoneCode: String? = null

        @SerializedName("referral_code")
        private val referralCode: String? = null
    }
}