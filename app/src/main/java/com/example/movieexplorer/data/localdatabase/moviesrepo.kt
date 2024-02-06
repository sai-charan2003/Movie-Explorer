package com.example.movieexplorer.data.localdatabase

import androidx.lifecycle.LiveData

class moviesrepo(private val moviesDao: MoviesDao) {
    val movies: LiveData<List<MovieItemModel>> = moviesDao.getAllMovies()
    suspend fun insert(movieItemModel: List<MovieItemModel>){
        moviesDao.insert(movieItemModel)
    }

}