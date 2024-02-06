package com.example.movieexplorer.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.movieexplorer.data.network.Apiservice
import com.example.movieexplorer.viewmodel.moviesviewmodel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun loadMoreItems(viewmodel: moviesviewmodel, context: Context) {
    val sharedPreferences: SharedPreferences =context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var pagenumber=sharedPreferences.getInt("pagenumber",1)
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()
        .create(Apiservice::class.java)

    val response = retrofit.getmovieslist(pagenumber)
    if(response.isSuccessful){
        pagenumber=pagenumber+1
        sharedPreferences.edit().putInt("pagenumber",pagenumber).apply()
        Log.d("TAG", "loadMoreItems: $pagenumber")
    }
    Log.d("TAG", "onCreate: ${response.body()}")
    response.body()?.let { viewmodel.insert(it.results) }
}