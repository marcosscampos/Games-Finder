package br.edu.infnet.gamesfinder.model

import com.google.gson.annotations.SerializedName

class GameDetail(
    @SerializedName("id")
    var id: Int,

    @SerializedName("slug")
    var slug: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("name_original")
    var name_original: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("released")
    var released: String,

    @SerializedName("background_image")
    var backgroundImage: String,

    @SerializedName("rating")
    var rating: Double = 0.0
)