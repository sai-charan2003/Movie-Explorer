package com.example.movieexplorer.data.network

import com.example.movieexplorer.data.model.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {
    @GET("trending/movie/day?api_key=3aa2c997ec3eb7851fa0e377b062b620")
    suspend fun getmovieslist(
        @Query("page")
        page:Int



    ):Response<Movies>
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}