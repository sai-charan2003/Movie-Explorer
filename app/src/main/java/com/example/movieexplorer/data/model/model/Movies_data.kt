package com.example.movieexplorer.data.model.model

import com.example.movieexplorer.data.localdatabase.MovieItemModel

data class Movies(
    val page: Int,
    val results: List<MovieItemModel>,
    val total_pages: Int,
    val total_results: Int

)
