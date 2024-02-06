package com.example.movieexplorer.data.model.model

import com.example.movieexplorer.data.model.movie_details_data.Genre

data class moviedetials(
    val poster_path:String,
    val title:String,
    val runtime:String,
    val overview:String,
    val genres:List<Genre>,
    val release_date:String

)
