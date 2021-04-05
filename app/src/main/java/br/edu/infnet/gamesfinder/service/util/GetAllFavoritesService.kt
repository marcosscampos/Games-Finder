package br.edu.infnet.gamesfinder.service.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import br.edu.infnet.gamesfinder.service.GameFinderService
import java.io.File
import java.lang.Exception

class GetAllFavoritesService(val context: Context) : AsyncTask<Unit, Unit, Unit>() {
    private val TAG = "GetAllFavorites"
    private val filename = "games_favorites.txt"

    override fun doInBackground(vararg params: Unit?) {
        val dir = context.filesDir
        val file = File(dir, filename)

        try {
            Log.d(TAG, "Trying to get all favorites in file $file")
            GameFinderService.favs = parseFavorites(file.readText())
        } catch (e: Exception) {
            Log.d(TAG, "Failed to get all favorites. Error: ${e.message}")
        }
    }

    private fun parseFavorites(data: String): ArrayList<Int> {
        return if (data == "") {
            ArrayList()
        } else {
            data.split(",").dropLast(1).map {
                it.toInt()
            } as ArrayList<Int>
        }

    }
}