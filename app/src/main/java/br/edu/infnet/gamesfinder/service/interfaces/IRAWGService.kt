package br.edu.infnet.gamesfinder.service.interfaces

import br.edu.infnet.gamesfinder.model.GameDetail
import br.edu.infnet.gamesfinder.model.GamesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRAWGService {
    @GET("games?key=306f235f06d7459ebd807935e5d2373a&page_size=15")
    fun getAllGames() : Call<GamesResult>

    @GET("games/{id}?key=306f235f06d7459ebd807935e5d2373a")
    fun getDetails(@Path("id") id: Int) : Call<GameDetail>

    @GET("games?key=306f235f06d7459ebd807935e5d2373a&page_size=15")
    fun getGameByName(@Query("search") query: String) : Call<GamesResult>
}