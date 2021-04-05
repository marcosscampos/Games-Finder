package br.edu.infnet.gamesfinder.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.gamesfinder.view.activities.GameDetailsActivity
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.model.Game
import com.squareup.picasso.Picasso

class SearchGameAdapter(private val context: Context, private val filteredGames: List<Game>) : RecyclerView.Adapter<SearchGameAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameTitle: TextView = itemView.findViewById(R.id.gameSearchTitle)
        val gameImage: ImageView = itemView.findViewById(R.id.gameImage)
        val gameRelease: TextView = itemView.findViewById(R.id.gameSearchDate)
        val gameOverview: TextView = itemView.findViewById(R.id.GameSearchOverview)
        val gameCard: CardView = itemView.findViewById(R.id.gameSearchCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.search_game_results, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = filteredGames[position]
        val gameImage = game.backgroundImage

        Picasso.get().load(gameImage).into(holder.gameImage)

        holder.gameTitle.text = game.name
        holder.gameOverview.text = game.description
        holder.gameRelease.text = game.released

        holder.gameCard.setOnClickListener {
            val intent = Intent(context, GameDetailsActivity::class.java)
            intent.putExtra("id", game.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredGames.size
}