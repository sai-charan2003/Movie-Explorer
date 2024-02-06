package com.example.movieexplorer.workermanager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movieexplorer.data.localdatabase.MoviesRoomDatabase
import com.example.movieexplorer.data.network.Apiservice
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class workmanager(context: Context, parameterName: WorkerParameters):
    CoroutineWorker(context,parameterName) {
    val context=context

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun doWork(): Result {
        val sharedPreferences: SharedPreferences =context.getSharedPreferences("settings",Context.MODE_PRIVATE)
        var pagenumber=sharedPreferences.getInt("pagenumber",1)
        val db = Room.databaseBuilder(
            applicationContext,
            MoviesRoomDatabase::class.java, "movies_database"
        ).build()


        GlobalScope.launch(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
                .create(Apiservice::class.java)

            val response = retrofit.getmovieslist(pagenumber)
            if(response.isSuccessful){
                pagenumber=pagenumber+1
                sharedPreferences.edit().putInt("pagenumber",pagenumber).apply()

            }
            response.body()?.let { db.getMovieDao().insert(it.results) }




        }
        return Result.success()
    }
}