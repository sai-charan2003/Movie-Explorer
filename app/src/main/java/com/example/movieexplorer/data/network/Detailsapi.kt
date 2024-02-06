package com.example.movieexplorer.data.network

import com.example.movieexplorer.data.model.model.moviedetials
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface detailsapi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String="3aa2c997ec3eb7851fa0e377b062b620"
    ): moviedetials
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}