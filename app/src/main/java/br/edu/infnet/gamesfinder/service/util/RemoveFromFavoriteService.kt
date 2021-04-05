package br.edu.infnet.gamesfinder.service.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import br.edu.infnet.gamesfinder.service.GameFinderService
import java.lang.Exception

class RemoveFromFavoriteService(val context: Context) : AsyncTask<Int, Int, Unit>() {
    private val TAG = "RemoveFromFavorite"
    private val filename = "games_favorites.txt"
    private var success = false
    private var favorites = ArrayList<Int>()

    override fun doInBackground(vararg params: Int?) {
        Log.d(TAG, "Removing from favorite")

        favorites = GameFinderService.favs.filter { it != params[0] } as ArrayList<Int>
        val data = favorites.joinToString() + ","
        val fileExists = context.fileList().contains(filename)
        val mode = if (fileExists) Context.MODE_APPEND else Context.MODE_PRIVATE

        try {
            context.openFileOutput(filename, mode).use {
                Log.d(TAG, "Saving file... $data")
                it.write(data.toByteArray())
                success = true
            }
        } catch (e: Exception) {
            success = false
        }
    }

    override fun onPostExecute(result: Unit?) {
        if (success) GameFinderService.favs = favorites
    }
}