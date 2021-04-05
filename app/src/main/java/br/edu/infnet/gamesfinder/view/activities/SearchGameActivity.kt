package br.edu.infnet.gamesfinder.view.activities

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.adapter.SearchGameAdapter
import br.edu.infnet.gamesfinder.model.GamesResult
import br.edu.infnet.gamesfinder.service.GameFinderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_game)

        if(Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also(this::searchGames)
        }
    }

    private fun searchGames(query: String) {
        val recyclerView = findViewById<RecyclerView>(R.id.searchGameRecyclerView)
        val call = GameFinderService.service.getGameByName(query)

        call.enqueue(object : Callback<GamesResult> {
            override fun onResponse(call: Call<GamesResult>, response: Response<GamesResult>) {
                if (response.isSuccessful) {
                    try {
                        val gamesFound = response.body()!!.results
                        recyclerView.layoutManager = LinearLayoutManager(this@SearchGameActivity, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = SearchGameAdapter(this@SearchGameActivity, gamesFound)
                    } catch (e: Exception) {
                        Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<GamesResult>, t: Throwable) {
                Log.d("GameDetailsActivity", "Error on search a game from webservice.")
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}