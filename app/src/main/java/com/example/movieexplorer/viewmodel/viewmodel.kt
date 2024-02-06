package com.example.movieexplorer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.movieexplorer.data.localdatabase.MovieItemModel
import com.example.movieexplorer.data.localdatabase.MoviesRoomDatabase
import com.example.movieexplorer.data.localdatabase.moviesrepo
import com.example.movieexplorer.data.network.Apiservice
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject



class moviesviewmodel (context: Context) :ViewModel(){
    private val repo:moviesrepo

    val allmovies:LiveData<List<MovieItemModel>>
    init {
        val dao=MoviesRoomDatabase.getDatabase(context).getMovieDao()
        repo=moviesrepo(dao)
        allmovies=repo.movies
    }
    fun insert(MovieItemModel:List<MovieItemModel>)= viewModelScope.launch(Dispatchers.IO) {
        repo.insert(MovieItemModel)
    }




}

