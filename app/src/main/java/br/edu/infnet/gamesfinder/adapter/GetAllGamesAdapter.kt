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

class GetAllGamesAdapter(private val context: Context, private val games: ArrayList<Game>) : RecyclerView.Adapter<GetAllGamesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameCardTitle : TextView = itemView.findViewById(R.id.gameCardTitle)
        val gameImage : ImageView = itemView.findViewById(R.id.gameImage)
        val gameCardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.games_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        val gameTitle = game.name
        val gameImage = game.backgroundImage

        holder.gameCardTitle.text = gameTitle
        Picasso.get().load(gameImage).fit().into(holder.gameImage)

        holder.gameCardView.setOnClickListener {
            val intent = Intent(context, GameDetailsActivity::class.java)
            intent.putExtra("id", game.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = games.size
}