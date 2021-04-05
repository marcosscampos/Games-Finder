package br.edu.infnet.gamesfinder.model

import com.google.gson.annotations.SerializedName

class Game(
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
    var backgroundImage: String
)