package br.edu.infnet.gamesfinder.service

import br.edu.infnet.gamesfinder.service.interfaces.IRAWGService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GameFinderService {
    private const val baseUrl = "https://api.rawg.io/api/"
    var favs: ArrayList<Int> = ArrayList()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
    val service: IRAWGService = retrofit.create(IRAWGService::class.java)
}