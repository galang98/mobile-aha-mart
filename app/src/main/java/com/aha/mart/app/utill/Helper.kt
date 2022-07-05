package com.aha.mart.app.utill

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.palette.graphics.Palette
import androidx.preference.PreferenceManager
import com.aha.mart.app.data.network.model.response.UserResponse
import com.google.gson.Gson
import java.util.regex.Pattern

//Local data
fun helperSaveUserAndToken(context: Context?, user: UserResponse?) {
    val jsonUser = Gson().toJson(user)
    val sp = PreferenceManager.getDefaultSharedPreferences(context!!).edit()
    sp.putString(Constants.HELPER_USER,jsonUser)
    sp.putString(Constants.HELPER_TOKEN,jsonUser)
    sp.apply()
}

fun helperGetUserAndToken(context: Context?) : UserResponse{
    val sp = PreferenceManager.getDefaultSharedPreferences(context!!)
    val jsonUser = sp.getString(Constants.HELPER_USER, "")
    val gson = Gson()
    val user = gson.fromJson(jsonUser, UserResponse::class.java)
    Log.d("data", "user : " + Gson().toJson(user))
    return user
}

fun helperIsUserLoggedIn(context: Context?): Boolean {
    val sp = PreferenceManager.getDefaultSharedPreferences(context!!)
    val jsonUser = sp.getString(Constants.HELPER_USER, "")
    return jsonUser != null && jsonUser.isNotEmpty()
}

fun helperLogout(context: Context?){
    val sp = PreferenceManager.getDefaultSharedPreferences(context!!).edit()
    sp.remove(Constants.HELPER_USER)
    sp.remove(Constants.HELPER_TOKEN)
    sp.apply()
}

//Validation
fun helperPhoneValidation(phone: String): Boolean {
    val phonePattern = "((([8])[\\d]{8,12})|([+][6][2](?=.*[\\d])[\\d]{8,12}))"
    val pattern = Pattern.compile(phonePattern)
    val matcher = pattern.matcher(phone)
    return matcher.matches()
}

//Util
fun getDominantColor(bitmap: Bitmap?): Int {
    val builder = Palette.Builder(bitmap!!)
    val defaultValue = 0xFFFFFF
    val p = builder.generate()
    return p.getVibrantColor(defaultValue)
}
