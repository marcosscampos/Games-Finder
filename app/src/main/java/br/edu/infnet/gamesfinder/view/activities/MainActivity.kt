package br.edu.infnet.gamesfinder.view.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.adapter.GetAllGamesAdapter
import br.edu.infnet.gamesfinder.model.GamesResult
import br.edu.infnet.gamesfinder.service.GameFinderService
import br.edu.infnet.gamesfinder.service.util.GetAllFavoritesService
import br.edu.infnet.gamesfinder.view.fragments.UserFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progress = findViewById<ProgressBar>(R.id.progressBar)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_main)

        progress.visibility = View.VISIBLE

        val call = GameFinderService.service.getAllGames()
        getLoggerUser(UserFragment())

        call.enqueue(object : Callback<GamesResult> {
            override fun onResponse(call: Call<GamesResult>, response: Response<GamesResult>) {
                GetAllFavoritesService(this@MainActivity).execute()
                val game = response.body()!!.results

                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                recyclerView.adapter = GetAllGamesAdapter(this@MainActivity, game)
                progress.visibility = View.GONE
            }

            override fun onFailure(call: Call<GamesResult>, t: Throwable) {
                Log.d("MainActivity", "Error on get all games from webservice.")
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                progress.visibility = View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.searchBar).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun getLoggerUser(user: UserFragment) {
        val bundle = Bundle()
        val name = intent.getStringExtra("name")
        bundle.putString("name", name)

        user.setArguments(bundle)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, user)
            .commit()
    }
}