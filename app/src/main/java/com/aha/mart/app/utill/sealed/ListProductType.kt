package com.aha.mart.app.utill.sealed

sealed class ListProductType{
    object Flashsale : ListProductType()
    object Common : ListProductType()
    object Newest : ListProductType()
}
