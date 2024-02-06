package com.example.movieexplorer.data.localdatabase

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*



@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(moviesItemModel: List<MovieItemModel>)

    @Delete
    fun delete(moviesItemModel: MovieItemModel)

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<MovieItemModel>>
    @Query("SELECT * FROM movie_table")
    fun pagingSource(): PagingSource<Int, MovieItemModel>


}