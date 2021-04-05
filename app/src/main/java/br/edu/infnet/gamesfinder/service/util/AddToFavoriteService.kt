package br.edu.infnet.gamesfinder.service.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import br.edu.infnet.gamesfinder.service.GameFinderService
import java.lang.Exception

class AddToFavoriteService(val context: Context) : AsyncTask<Int, Unit, Int>() {
    private val TAG = "AddToFavorite"
    private val filename = "games_favorites.txt"
    private var success = false

    override fun doInBackground(vararg params: Int?): Int {
        Log.d(TAG, "Adding $params...")

        val data = "${params.first()}"
        val fileExists = context.fileList().contains(filename)
        val mode = if (fileExists) Context.MODE_APPEND else Context.MODE_PRIVATE

        try {
            context.openFileOutput(filename, mode).use {
                Log.d(TAG, "Saving file $data...")
                it.write(data.toByteArray())
                success = true
            }
            success = true
        } catch (e: Exception) {
            Log.d(TAG, "Failed to save file. Error: ${e.message}")
            success = false
        }

        return data.split(",").first().toInt()
    }

    override fun onPostExecute(id: Int) {
        if (success) GameFinderService.favs.add(id)
    }

}