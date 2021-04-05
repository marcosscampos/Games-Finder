package br.edu.infnet.gamesfinder.model

import com.google.gson.annotations.SerializedName

class GamesResult(
    @SerializedName("results")
    var results: ArrayList<Game> = ArrayList(),

    @SerializedName("next")
    var next: String,

    @SerializedName("previous")
    var previous: String
)