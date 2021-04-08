package br.edu.infnet.gamesfinder.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.model.GameDetail
import br.edu.infnet.gamesfinder.service.GameFinderService
import br.edu.infnet.gamesfinder.service.util.AddToFavoriteService
import br.edu.infnet.gamesfinder.service.util.RemoveFromFavoriteService
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        val service = GameFinderService.service
        val id = intent.extras?.getInt("id")!!

        val call = service.getDetails(id)

        call.enqueue(object : Callback<GameDetail> {
            override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {
                if (response.isSuccessful) {
                    try {
                        val game = response.body()!!
                        render(game)
                    } catch (e: Exception) {
                        Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                Log.d("GameDetailsActivity", "Error on get a game from webservice.")
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })

        findViewById<LikeButton>(R.id.addFavoriteButton).setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                addToFavorite(id)
            }

            override fun unLiked(likeButton: LikeButton?) {
                removeFromFavorite(id)
            }
        })
    }

    private fun render(game: GameDetail) {
        val gameImage = game.backgroundImage

        findViewById<TextView>(R.id.detailsGame).text = game.name
        findViewById<TextView>(R.id.gameDetailsText).text = game.description
        findViewById<LikeButton>(R.id.addFavoriteButton).isLiked = GameFinderService.favs.contains(game.id)
        findViewById<RatingBar>(R.id.RatingBar).rating = ((game.rating / 5) * 100).toFloat()
        findViewById<TextView>(R.id.releaseDateGame).text = game.released.split("-").first()

        Picasso.get().load(gameImage).fit().into(findViewById<ImageView>(R.id.gameImageDetail))
    }

    private fun addToFavorite(id: Int) = AddToFavoriteService(this).execute(id)

    private fun removeFromFavorite(id: Int) = RemoveFromFavoriteService(this).execute(id)

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}