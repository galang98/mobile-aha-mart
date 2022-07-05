package com.aha.mart.app.data.network.model.response

class ProductResponse {
    var image : Int = 0
    var name : String = ""

    constructor(image: Int, name: String) {
        this.image = image
        this.name = name
    }
}